package se.umu.cs.emli.View;

import se.umu.cs.emli.Model.Channel;
import javax.swing.*;
import java.awt.*;

/**
 * Class for building a JPanel with buttons for avaliable channels.
 */
public class BuildChannelPanel {
    private final JList<Channel> list;
    private final JPanel channelPanel;

    public BuildChannelPanel(JList<Channel> list){
        channelPanel = new JPanel(new BorderLayout());
        this.list = list;
        channelPanel.add(buildHeader(),BorderLayout.NORTH);
        channelPanel.add(buildList(),BorderLayout.CENTER);
    }

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

        //TODO: Update this description-text :)
        JTextArea description = new JTextArea("Här kan du få information om Svergies Radios kanaler och deras tablåer" +
                ". blabla nåt mer?" +
                "Tryck här nere eller välj kanal uppe i menyn. Lite mer " +
                "hjälp här om hur man använder programmet vore bra!");
        description.setFont(new Font("Inter", Font.PLAIN, 15));
        description.setLineWrap(true);
        description.setMaximumSize(new Dimension(700, 600));
        description.setOpaque(false);
        description.setEditable(false);
        description.setAlignmentX(Component.LEFT_ALIGNMENT);

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

        return new JScrollPane(panel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }
}