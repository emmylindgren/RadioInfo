package se.umu.cs.emli.Controller;

import se.umu.cs.emli.Model.ApiChannelParser;
import se.umu.cs.emli.View.View;

import java.sql.SQLOutput;

public class Controller {
    private View view;

    public Controller(){
        view = new View("RadioInfo");
        //TODO: Nedan är hämtningen av radiokanalerna. Updatera GUI med dessa. Sätt lyssnare?
        //new ApiChannelParser().execute();
        setUpMenuListeners();
    }

    //TODO: Set up this.
    private void setUpMenuListeners(){
        view.setUpdateMenuItemListener(e -> {System.out.println("Klick på uppdatera");});
        view.setChannelMenuItemListener(e -> {System.out.println("Klick på channels");});
        view.setCancelItemListener(e -> {System.out.println("Klick på avsluta");});
    }

}
