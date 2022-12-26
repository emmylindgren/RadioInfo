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

public class TableauWorker extends SwingWorker<ArrayList<Program>,Object> {
    private ApiTableauParser parser;
    private ProgramTableModel tableau;
    private MainView view;
    private String errorMsg;
    private Channel chan;
    private ScheduledExecutorService scheduler;

    public TableauWorker(Channel chan, MainView view, ScheduledExecutorService scheduler){
        this.chan = chan;
        this.tableau = this.chan.getTableau();
        this.parser = new ApiTableauParser(tableau,this.chan.getTableauURL());
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
                chan.setHasHashedTableau(false);
            }
            else{
                tableau.setTableau(tableauList);
                chan.setHasHashedTableau(true);
                System.out.println("Tablån "+ chan.getName() +" är satt");
            }
        } catch (InterruptedException | ExecutionException e) {
            view.showInformation("Något gick fel vid inläsningen av tablån för "+ chan.getName());
        }
    }
}