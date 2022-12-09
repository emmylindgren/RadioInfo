package se.umu.cs.emli.Controller;

import se.umu.cs.emli.Model.ApiChannelParser;
import se.umu.cs.emli.View.View;

public class Controller {
    private View view;

    public Controller(){
        view = new View("RadioInfo");
        //Nedan är hämtningen av radiokanalerna.
        //new ApiChannelParser().execute();
    }
}
