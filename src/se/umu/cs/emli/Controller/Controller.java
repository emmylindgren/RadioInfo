package se.umu.cs.emli.Controller;

import se.umu.cs.emli.Model.ApiChannelParser;
import se.umu.cs.emli.Model.Channel;
import se.umu.cs.emli.Model.ChannelListModel;
import se.umu.cs.emli.Model.ProgramTableModel;
import se.umu.cs.emli.View.MainView;
import javax.swing.*;

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

    //TODO: Set up the rest of menu listeners.
    private void setUpMenuListeners(){
        view.setUpdateMenuItemListener(e -> {System.out.println("Klick på uppdatera");});
        view.setChannelMenuItemListener(e -> {view.showChannelView();});
        view.setCancelItemListener(e -> {System.out.println("Klick på avsluta");});
    }

    //TODO: This is what should load the tablau for each channel. And update UI with that tablemodel:)
    private void setUpJListListener(){
        view.setChannelListListener(event -> {
            if (!event.getValueIsAdjusting()){
                JList source = (JList)event.getSource();
                if(source.getSelectedIndex() >= 0){
                    Channel chan = (Channel)source.getSelectedValue();
                    System.out.println("Vald kanal: " + chan.getName());
                    //Starta en vy med denna och ladda in kanalinfo
                    // där sätta lyssnare på den tabellen : om man väljer en ska popup med mer info
                    //visas: bild och beskrivning: bild laddas in på tråd?
                    //TODO: Ladda in tablån, tog bort de nu för inte ska hämta för mycket hehe :)
                    //TODO: Tablån ska sättas lyssnare på också! Visa bild och beskrivning.
                    ProgramTableModel model = chan.getTableau();
                    view.setTableau(model);
                    view.setTableauInfo(chan.getName(),chan.getTagline(),
                            chan.getBiggerImageIcon());
                    view.showTableauView();
                    source.clearSelection();
                }
            }
        });
    }
}
