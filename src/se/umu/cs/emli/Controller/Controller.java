package se.umu.cs.emli.Controller;

import se.umu.cs.emli.Model.ApiChannelParser;
import se.umu.cs.emli.Model.Channel;
import se.umu.cs.emli.Model.ChannelListModel;
import se.umu.cs.emli.Model.ProgramTableModel;
import se.umu.cs.emli.View.MainView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Controller {
    private MainView view;
    private ChannelListModel channelList;

    public Controller(){

        channelList = new ChannelListModel();
        view = new MainView(channelList);
        ApiChannelParser parser = new ApiChannelParser(view,channelList);
        parser.execute();

        setUpMenuListeners();
        setUpJListListener();
    }

    //TODO: Set up this.
    private void setUpMenuListeners(){
        view.setUpdateMenuItemListener(e -> {System.out.println("Klick på uppdatera");});
        view.setChannelMenuItemListener(e -> {view.showChannelView();});
        view.setCancelItemListener(e -> {System.out.println("Klick på avsluta");});
    }

    //TODO: This is what should load the tablau for each channel. And update UI with that tablemodel:)
    private void setUpJListListener(){
        view.setChannelListListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()){
                    JList source = (JList)event.getSource();
                    //Chan here is the object on which to load the tablau from! I think :)
                    Channel chan = (Channel)source.getSelectedValue();
                    String selected = "Vald kanal: " + chan.getName();
                    System.out.println(selected);

                    //Starta en vy med denna och ladda in kanalinfo
                    // där sätta lyssnare på den tabellen : om man väljer en ska popup med mer info
                    //visas: bild och beskrivning: bild laddas in på tråd?
                    ProgramTableModel model = chan.getTableau();
                    view.showTableau();
                }
            }
        });
    }

}
