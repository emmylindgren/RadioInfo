package se.umu.cs.emli.Controller;

import se.umu.cs.emli.Model.ApiChannelParser;
import se.umu.cs.emli.Model.Channel;
import se.umu.cs.emli.Model.ChannelListModel;
import se.umu.cs.emli.View.View;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.sql.SQLOutput;

public class Controller {
    private View view;
    private ChannelListModel channelList;

    public Controller(){

        channelList = new ChannelListModel();
        view = new View("RadioInfo", channelList);
        ApiChannelParser parser = new ApiChannelParser(view,channelList);
        parser.execute();

        setUpMenuListeners();
        setUpJListListener();
    }

    //TODO: Set up this.
    private void setUpMenuListeners(){
        view.setUpdateMenuItemListener(e -> {System.out.println("Klick på uppdatera");});
        view.setChannelMenuItemListener(e -> {System.out.println("Klick på channels");});
        view.setCancelItemListener(e -> {System.out.println("Klick på avsluta");});
    }

    //TODO: This is what should load the tablau for each channel.
    private void setUpJListListener(){
        view.setChannelListListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()){
                    JList source = (JList)event.getSource();
                    //Chan here is the object on which to load the tablau from! I think :)
                    Channel chan = (Channel)source.getSelectedValue();
                    //String selected = source.getSelectedValue().toString();
                    String selected = chan.getName();
                    System.out.println(selected);
                }
            }
        });
    }

}
