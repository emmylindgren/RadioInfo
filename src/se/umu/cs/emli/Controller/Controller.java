package se.umu.cs.emli.Controller;

import se.umu.cs.emli.Model.*;
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
        //setUpJTableListener();
    }

    //TODO: Set up menu option update listener.
    private void setUpMenuListeners(){
        view.setUpdateMenuItemListener(e -> {System.out.println("Klick på uppdatera");});
        view.setChannelMenuItemListener(e -> {view.showChannelView();});
        view.setCancelItemListener(e -> {System.exit(0);});
    }

    private void setUpJListListener(){
        view.setChannelListListener(event -> {
            if (!event.getValueIsAdjusting()){
                JList source = (JList)event.getSource();
                if(source.getSelectedIndex() >= 0){
                    Channel chan = (Channel)source.getSelectedValue();

                    ProgramTableModel model = chan.getTableau();
                    /*
                    Förslag:
                    if(!chan.hasLoadedTableau){
                        ny tableauworker(chan, true)
                    }

                    sen om man ska uppdatera : swingworker för att uppdatera?
                        ny tableauworker(chan, false)
                        Kom ihåg att kolla också om url är null först :)
                     */
                    view.setTableau(model);
                    view.setTableauInfo(chan.getName(),chan.getTagline(),
                            chan.getBiggerImageIcon());
                    view.showTableauView();

                    source.clearSelection();
                }
            }
        });
    }
    //TODO: Tablån ska sättas lyssnare på också! Visa bild och beskrivning. Detta nedan fungerar ej just nu:)
    // bör väl rimligtvis även de göras på tråd kanske, just att ladda in bild :)
    /*
    private void setUpJTableListener(){
        view.setTableauTableListener(event -> {
            JTable source = (JTable)event.getSource();
            if (!event.getValueIsAdjusting()){
                //Program program = (Program)source.getSelectedValue();
                System.out.println(source.getValueAt(source.getSelectedRow(), 0).toString());

            }
           // System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
        });
    }*/
}
