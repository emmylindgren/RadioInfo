package se.umu.cs.emli.Controller;

import se.umu.cs.emli.Model.*;
import se.umu.cs.emli.View.MainView;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Controller {
    private final MainView view;
    private ChannelListModel channelList;

    public Controller(){
        channelList = new ChannelListModel();
        view = new MainView(channelList);
        new ChannelWorker(view,channelList).execute();

        setUpMenuListeners();
        setUpJListListener();
        setUpJTableListener();
    }

    private void setUpMenuListeners(){
        view.setChannelMenuItemListener(e -> {view.showChannelView();});
        view.setCancelItemListener(e -> System.exit(0));
    }
    private void setUpJListListener(){
        view.setChannelListListener(event -> {
            if (!event.getValueIsAdjusting()){
                JList source = (JList)event.getSource();
                if(source.getSelectedIndex() >= 0){
                    Channel chan = (Channel)source.getSelectedValue();

                    if(chan.getTableauURL() == null){
                        view.showInformation("Det finns ingen tablå för den här kanalen");
                    }
                    else{
                        ProgramTableModel model = chan.getTableau();
                        updateView(chan, model);
                        if(!chan.hasHashedTableau()) {
                            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
                            scheduler.scheduleAtFixedRate(() -> { new TableauWorker(chan,view,scheduler).execute();},
                                    0, 60, TimeUnit.MINUTES);
                        }
                    }
                    source.clearSelection();
                }
            }
        });
    }
    //TODO: Fixa detta.Tablån ska sättas lyssnare på också! Visa bild och beskrivning. Detta nedan fungerar ej just nu:)
    // bör väl rimligtvis även de göras på tråd kanske, just att ladda in bild :)
    private void setUpJTableListener(){
        view.setTableauTableListener((new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable)e.getSource();
                    ProgramTableModel model = (ProgramTableModel) target.getModel();
                    System.out.println(model.getProgramFromRow(target.getSelectedRow()));
                }
            }
        }));
    }
    private void updateView(Channel chan, ProgramTableModel model) {
        view.setTableau(model);
        view.setTableauInfo(chan.getName(), chan.getTagline(),
                chan.getBiggerImageIcon());
        view.showTableauView();
        view.replaceUpdateButtonListener(e -> manualUpdate(chan));
        view.replaceUpdateMenuItemListener(e -> manualUpdate(chan));
    }

    private void manualUpdate(Channel chan){
        new TableauWorker(chan,view,null).execute();
    }
}