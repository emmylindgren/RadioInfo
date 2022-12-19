package se.umu.cs.emli.Controller;

import se.umu.cs.emli.Model.ApiTableauParser;
import se.umu.cs.emli.Model.Channel;
import javax.swing.*;

public class TableauWorker extends SwingWorker<Object,Object> {
    private ApiTableauParser parser;
    private boolean repeats;

    public TableauWorker(Channel chan, boolean repeats){
        this.parser = new ApiTableauParser(chan.getTableau(),chan.getTableauURL());
        this.repeats = repeats;
    }
    @Override
    protected Object doInBackground() throws Exception {
        //Här fånga undantag i typ done eller skicka till publish eller nå?
        if(repeats){
            //start timer with parser.load()
        }
        else{
            //just do it :)
            parser.loadTableau();
        }
        return null;
    }
}
