package se.umu.cs.emli.Controller;

import org.xml.sax.SAXException;
import se.umu.cs.emli.Model.ApiTableauParser;
import se.umu.cs.emli.Model.Channel;
import se.umu.cs.emli.Model.Program;
import se.umu.cs.emli.Model.ProgramTableModel;
import se.umu.cs.emli.View.MainView;
import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Swingworker to load tableau for a channel and update the ProgramTableModel (tableau) for that channel when
 * done.
 * If something goes wrong with the parsing then the worker is responsible for informing the user and also
 * shutting down the scheduler that schedules the swingworker once an hour, so that no more involuntary updates
 * are done after running in to a problem.
 * @author Emmy Lindgren, id19eln.
 */
public class TableauWorker extends SwingWorker<ArrayList<Program>,Object> {
    private final ApiTableauParser parser;
    private final ProgramTableModel tableau;
    private final MainView view;
    private String errorMsg;
    private final Channel chan;
    private final ScheduledExecutorService scheduler;

    /**
     * Constructor for TableauWorker.
     * @param chan, the channel from which to get the tableauURL from and also load the Tableau on.
     * @param view, the view object.
     * @param scheduler, the ScheduledExecutorService on which the TableauWorker is scheduled on.
     */
    public TableauWorker(Channel chan, MainView view, ScheduledExecutorService scheduler){
        this.chan = chan;
        this.tableau = this.chan.getTableau();
        this.parser = new ApiTableauParser(this.chan.getTableauURL());
        this.view = view;
        this.errorMsg = "";
        this.scheduler = scheduler;
    }
    @Override
    protected ArrayList<Program> doInBackground(){
        try {
            return parser.loadTableau();
        } catch (ParserConfigurationException | SAXException e) {
            errorMsg = "Något gick fel vid inläsningen av tablån för " + chan.getName();
            return null;
        } catch (IOException e) {
            errorMsg = "Tablån för "+ chan.getName() + " kunde inte hittas. Kontrollera din uppkoppling.";
            return null;
        }
    }
    @Override
    protected void done() {
        try {
            ArrayList<Program> tableauList = get();
            if(tableauList == null){
                view.showInformation(errorMsg);
                if(scheduler != null) scheduler.shutdown();
                chan.setHasCachedTableau(false);
            }
            else{
                tableau.setTableau(tableauList);
                chan.setHasCachedTableau(true);
            }
        } catch (InterruptedException | ExecutionException e) {
            view.showInformation("Något gick fel vid inläsningen av tablån för "+ chan.getName());
        }
    }
}