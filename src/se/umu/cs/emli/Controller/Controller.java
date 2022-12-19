package se.umu.cs.emli.Controller;

import se.umu.cs.emli.Model.ApiChannelParser;
import se.umu.cs.emli.Model.Channel;
import se.umu.cs.emli.Model.ChannelListModel;
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

    //TODO: Set up this.
    private void setUpMenuListeners(){
        view.setUpdateMenuItemListener(e -> {System.out.println("Klick på uppdatera");});
        view.setChannelMenuItemListener(e -> {view.showChannelView();});
        view.setCancelItemListener(e -> {System.out.println("Klick på avsluta");});
    }

    //TODO: This is what should load the tablau for each channel. And update UI with that tablemodel:)
    //TODO: FIXA! GÅR EJ ATT KLICKA PÅ TVÅ RADIOKANALER EFTER VARANDRA :)
    private void setUpJListListener(){

        view.setChannelListListener(event -> {
            if (!event.getValueIsAdjusting()){
                JList source = (JList)event.getSource();
                if(source.getSelectedIndex() >= 0){
                    //Chan here is the object on which to load the tablau from! I think :)
                    Channel chan = (Channel)source.getSelectedValue();
                    String selected = "Vald kanal: " + chan.getName();
                    System.out.println(selected);

                    //Starta en vy med denna och ladda in kanalinfo
                    // där sätta lyssnare på den tabellen : om man väljer en ska popup med mer info
                    //visas: bild och beskrivning: bild laddas in på tråd?
                    //TODO: Ladda in tablån, tog bort de nu för inte ska hämta för mycket hehe :)
                    //ProgramTableModel model = chan.getTableau();
                    view.setTableauInfo(chan.getName(),chan.getTagline(),
                            chan.getBiggerImageIcon());
                    view.showTableau();
                    source.clearSelection();
                }
            }
        });
    }

}
