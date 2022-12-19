package se.umu.cs.emli.View;

import se.umu.cs.emli.Model.Channel;
import se.umu.cs.emli.Model.ChannelListModel;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class View {
    private JFrame frame;
    private JMenuBar menuBar;
    private JList<Channel> list;
    private CardLayout cardLayout;
    private JPanel layoutPanel;

    //TODO: När ska visa upp programtablå så blir de: JTable jTable=new JTable(programList);
    //            JScrollPane sPane=new JScrollPane(jTable);
    public View(String title, ChannelListModel channelList){
        frame = new JFrame();
        frame.setTitle(title);
        frame.setSize(new Dimension(950, 600));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        list = new JList<>(channelList);

        cardLayout = new CardLayout();
        layoutPanel = new JPanel(cardLayout);

        this.menuBar = buildJMenuBar();
        frame.setJMenuBar(this.menuBar);
        frame.add(layoutPanel);

        buildTableauView();
        buildChannelView();

        showChannelView();
    }
    private void buildChannelView(){
        JPanel channelPanel = new JPanel(new BorderLayout());
        channelPanel.add(buildChannelViewHeader(),BorderLayout.NORTH);
        channelPanel.add(buildChannelList(),BorderLayout.CENTER);

        layoutPanel.add(channelPanel,"channel");
    }

    private void buildTableauView(){
        JPanel tableauPanel = new JPanel(new BorderLayout());
        tableauPanel.add(buildTableauViewHeader(),BorderLayout.NORTH);
        //tableauPanel.add(buildChannelList(),BorderLayout.CENTER);
        layoutPanel.add(tableauPanel,"tableau");
    }

    public void showTableau(){
        cardLayout.show(layoutPanel,"tableau");
    }
    public void showChannelView(){
        cardLayout.show(layoutPanel,"channel");
    }

    private JPanel buildTableauViewHeader(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15,10));

        JLabel header = new JLabel("NÄSTA !!");
        header.setAlignmentX(Component.LEFT_ALIGNMENT);
        header.setFont(new Font("Inter", Font.BOLD, 25));
        panel.add(header);
        return panel;
    }

    public JScrollPane buildChannelList(){
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0,10));

        list.setCellRenderer(new ChannelRenderer());

        panel.setMaximumSize((new Dimension(900, 600)));
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setVisibleRowCount(15);
        panel.add(list);

        JScrollPane scrollPane = new JScrollPane(panel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        return scrollPane;
    }

    public void setChannelListListener(ListSelectionListener listener){
        list.addListSelectionListener(listener);
    }

    private JPanel buildChannelViewHeader(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15,10));

        JLabel header = new JLabel("Radioinfo Sveriges Radio");
        header.setAlignmentX(Component.LEFT_ALIGNMENT);
        header.setFont(new Font("Inter", Font.BOLD, 25));

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


    private JMenuBar buildJMenuBar(){
        JMenuBar bar = new JMenuBar();
        JMenu menu   = new JMenu("Meny");
        JMenuItem item1 = new JMenuItem("Alla Kanaler");
        JMenuItem item2 = new JMenuItem("Uppdatera sidan");
        JMenuItem item3 = new JMenuItem("Avsluta");

        menu.add(item1);
        menu.add(item2);
        menu.addSeparator();
        menu.add(item3);
        bar.add(menu);
        return bar;
    }

    /**
     * Sets actionListener for first menu item in the menu.
     * @param actionListener = the listener to set.
     */
    public void setChannelMenuItemListener(ActionListener actionListener) {
        menuBar.getMenu(0).getItem(0).addActionListener(actionListener);
    }
    /**
     * Sets actionListener for second menu item in the menu.
     * @param actionListener = the listener to set.
     */
    public void setUpdateMenuItemListener(ActionListener actionListener) {
        menuBar.getMenu(0).getItem(1).addActionListener(actionListener);
    }
    /**
     * Sets actionListener for third menu item in the menu.
     * Separators excluded in indexing of menu.
     * @param actionListener = the listener to set.
     */
    public void setCancelItemListener(ActionListener actionListener) {
        menuBar.getMenu(0).getItem(3).addActionListener(actionListener);
    }
}
