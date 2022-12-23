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

public class TableauWorker extends SwingWorker<ArrayList<Program>,Object> {
    private ApiTableauParser parser;
    private ProgramTableModel tableau;
    private MainView view;
    private String errorMsg;
    private Channel chan;

    public TableauWorker(Channel chan, MainView view){
        this.chan = chan;
        this.tableau = this.chan.getTableau();
        this.parser = new ApiTableauParser(tableau,this.chan.getTableauURL());
        this.view = view;
        this.errorMsg = "";
    }
    @Override
    protected ArrayList<Program> doInBackground(){
        try {
            return parser.loadTableau();
        } catch (ParserConfigurationException e) {
            errorMsg = "Something went wrong";
            return null;
        } catch (IOException e) {
            errorMsg = "Something went wrong IO";
            return null;
        } catch (SAXException e) {
            errorMsg = "Something went wrong";
            return null;
        }
    }

    @Override
    protected void done() {
        try {
            ArrayList<Program> tableauList = get();
            if(tableauList == null){
                System.out.println(errorMsg);
                //Här vill vi att timer ska sluta och sätta hashashed till false.
                //chan.setHasHashedTableau(false);
                //exception used for testing i guess
                throw new RuntimeException();

                //display errorMsg och se till att startsidan visas? :)
                // och kasta exception så timer stängs av :)
            }
            else{
                System.out.println("setting tablå "+ chan.getName());
                tableau.setTableau(tableauList);
                chan.setHasHashedTableau(true);
            }
        } catch (InterruptedException e) {
            System.out.println("kastar1");
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            System.out.println("kastar2");
            throw new RuntimeException(e);
        }
    }
}
