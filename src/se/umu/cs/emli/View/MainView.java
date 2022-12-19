package se.umu.cs.emli.View;

import se.umu.cs.emli.Model.Channel;
import se.umu.cs.emli.Model.ChannelListModel;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView {
    private JFrame frame;
    private JMenuBar menuBar;
    private JList<Channel> channelJlist;
    private CardLayout cardLayout;
    private JPanel layoutPanel;

    //TODO: När ska visa upp programtablå så blir de: JTable jTable=new JTable(programList);
    //            JScrollPane sPane=new JScrollPane(jTable);
    public MainView(ChannelListModel channelList){
        buildFrameWithMenuBar();
        channelJlist = new JList<>(channelList);

        cardLayout = new CardLayout();
        layoutPanel = new JPanel(cardLayout);

        frame.add(layoutPanel);

        buildTableauView();
        buildChannelView();

        showChannelView();
    }

    private void buildFrameWithMenuBar() {
        frame = new JFrame();
        frame.setTitle("RadioInfo");
        frame.setSize(new Dimension(950, 600));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        this.menuBar = buildJMenuBar();
        frame.setJMenuBar(this.menuBar);
    }

    private void buildChannelView(){
        layoutPanel.add(new BuildChannelPanel(channelJlist).getPanel(),"channel");
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


    public void setChannelListListener(ListSelectionListener listener){
        channelJlist.addListSelectionListener(listener);
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
