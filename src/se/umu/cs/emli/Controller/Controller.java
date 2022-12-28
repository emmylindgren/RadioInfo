package se.umu.cs.emli.Controller;

import se.umu.cs.emli.Model.*;
import se.umu.cs.emli.View.MainView;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Controller-class for RadioInfo. Runs on the EDT.
 * Sets up the view and listeners.
 * @author Emmy Lindgren, id19eln.
 */
public class Controller {
    private final MainView view;
    public Controller(){
        ChannelListModel channelList = new ChannelListModel();
        view = new MainView(channelList);
        new ChannelWorker(view, channelList).execute();

        setUpMenuListeners();
        setUpJListListener();
        setUpJTableListener();
    }

    private void setUpMenuListeners(){
        view.setChannelMenuItemListener(e -> view.showChannelView());
        view.setCancelItemListener(e -> System.exit(0));
    }

    /**
     * Sets up listeners for JList with channels. When a channel is pressed the tableau is fetched from the channel
     * and set to the view. If no tableau was cached then data is fetched from the tableauURl of the channel, and
     * set to repeat once every hour.
     * If no tableauURL exists then the user is informed.
     */
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
                        setTableauView(chan, model);
                        if(!chan.hasCachedTableau()) {
                            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
                            scheduler.scheduleAtFixedRate(() -> new TableauWorker(chan,view,scheduler).execute(),
                                    0, 60, TimeUnit.MINUTES);
                        }
                    }
                    source.clearSelection();
                }
            }
        });
    }

    /**
     * Sets up listener for Tableau JTable. When a program in the tableau is double-clicked the programinformation
     * is fetched from the program, and information about the program is shown. If program does not yet have any
     * image-icon then a Swingworker is started to load the image and then show information about the program.
     */
    private void setUpJTableListener(){
        view.setTableauTableListener((new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable table = (JTable)e.getSource();
                    ProgramTableModel model = (ProgramTableModel) table.getModel();
                    Program program = model.getProgramFromRow(table.getSelectedRow());

                    view.setWaitCursor(true);
                    if(program.getImage() == null){
                        ProgramImageWorker worker = new ProgramImageWorker(program, ()->showProgramInfo(program));
                        worker.execute();
                    }
                    else{
                        showProgramInfo(program);
                    }
                }
            }
        }));
    }
    private void showProgramInfo(Program program){
        view.setWaitCursor(false);
        view.showProgramInfo(program.getName(), program.getDescription(), program.getStatus().toString(),
                program.getImage());
    }
    private void setTableauView(Channel chan, ProgramTableModel model) {
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