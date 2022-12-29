package se.umu.cs.emli.View;

import se.umu.cs.emli.Model.Channel;
import javax.swing.*;
import java.awt.*;
/**
 * Class for building a JPanel with buttons for available channels.
 * Contains a header with information about the RadioInfo-program and then a panel with all channels loaded.
 * @author Emmy Lindgren, id19eln.
 */
public class BuildChannelPanel {
    private final JList<Channel> list;
    private final JPanel channelPanel;
    /**
     * Builds a JPanel containing an information header and a listview of the channels.
     * @param list, the JListModel of channels to be shown in the JPanel.
     */
    public BuildChannelPanel(JList<Channel> list){
        channelPanel = new JPanel(new BorderLayout());
        this.list = list;
        channelPanel.add(buildHeader(),BorderLayout.NORTH);
        channelPanel.add(buildList(),BorderLayout.CENTER);
    }
    /**
     * Returns the built JPanel.
     * @return JPanel that's built in the constructor.
     */
    public JPanel getPanel(){
        return channelPanel;
    }
    private JPanel buildHeader(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15,10));

        JLabel header = new JLabel("Radioinfo Sveriges Radio");
        header.setAlignmentX(Component.LEFT_ALIGNMENT);
        header.setFont(new Font("Inter", Font.BOLD, 25));

        NiceTextArea description = new NiceTextArea("Här kan du se information om Svergies Radios kanaler och " +
                "deras tablåer. Klicka på en kanal för att se dess tablå. Tablån uppdateras sedan en gång i timmen, " +
                "men du kan själv uppdatera tablån med " +
                "hjälp av menyn eller uppdatera-knappen som finns på tablå-vyn.");
        description.setMaximumSize(new Dimension(850, 600));

        panel.add(header);
        panel.add(description);
        return panel;
    }
    private JScrollPane buildList(){
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0,10));

        list.setCellRenderer(new ChannelRenderer());

        panel.setMaximumSize((new Dimension(900, 600)));
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setVisibleRowCount(15);
        panel.add(list);
        JScrollPane pane = new JScrollPane(panel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.getVerticalScrollBar().setUnitIncrement(20);

        return pane;
    }
}