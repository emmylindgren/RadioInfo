package se.umu.cs.emli.View;

import se.umu.cs.emli.Model.Channel;
import se.umu.cs.emli.Model.ChannelListModel;
import se.umu.cs.emli.Model.ImageLoader;
import se.umu.cs.emli.Model.ProgramTableModel;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView {
    private JFrame frame;
    private JMenuBar menuBar;
    private JList<Channel> channelJList;
    private CardLayout cardLayout;
    private JPanel layoutPanel;
    private JLabel tableauHeader;
    private JTextArea tableauDescription;
    private JLabel tableauIconLabel;
    private JTable tableau;
    private  JButton updateButton;

    public MainView(ChannelListModel channelList){
        buildFrameWithMenuBar();
        channelJList = new JList<>(channelList);

        cardLayout = new CardLayout();
        layoutPanel = new JPanel(cardLayout);

        frame.add(layoutPanel);

        buildChannelView();
        buildTableauView();

        showChannelView();
    }

    public void showNoTableau(){
        Object[] options = {"OK"};
        JOptionPane.showOptionDialog(null, "Kanalen har ingen tillgänglig tablå", "Information",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);
    }

    private void buildFrameWithMenuBar() {
        frame = new JFrame();
        frame.setTitle("RadioInfo");
        frame.setSize(new Dimension(950, 600));
        //TODO: Set this back maybe?
        //frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        this.menuBar = buildJMenuBar();
        frame.setJMenuBar(this.menuBar);
    }

    private void buildChannelView(){
        layoutPanel.add(new BuildChannelPanel(channelJList).getPanel(),"channel");
    }

    private void buildTableauView(){
        JPanel tableauPanel = new JPanel(new BorderLayout());
        tableauPanel.add(buildTableauViewHeader(),BorderLayout.NORTH);
        tableauPanel.add(buildTableau(),BorderLayout.CENTER);
        layoutPanel.add(tableauPanel,"tableau");
    }

    private JPanel buildTableau(){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 15, 15,10));

        JPanel tableauInfo = new JPanel();
        tableauInfo.setLayout(new BorderLayout());
        tableauInfo.setBorder(BorderFactory.createEmptyBorder(0, 0, 5,0));
        JLabel header = new JLabel("Kanaltablå");
        header.setAlignmentX(Component.LEFT_ALIGNMENT);
        header.setFont(new Font("Inter", Font.BOLD, 25));

        updateButton = new JButton();
        updateButton.setIcon(new ImageLoader().loadRefreshIcon());

        tableauInfo.add(header,BorderLayout.CENTER);
        tableauInfo.add(updateButton,BorderLayout.EAST);
        panel.add(tableauInfo);

        tableau = new JTable();
        tableau.setRowHeight(30);
        JScrollPane scroll = new JScrollPane(tableau,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scroll,BorderLayout.CENTER);

        return panel;
    }

    public void setTableau(ProgramTableModel model){
        tableau.setModel(model);
        TableColumnModel columnModel = tableau.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(250);
        columnModel.getColumn(1).setPreferredWidth(20);
        columnModel.getColumn(2).setPreferredWidth(20);
    }
    public void setTableauInfo(String name,String tagline,ImageIcon image){
        tableauHeader.setText(name);
        tableauDescription.setText(tagline);
        tableauIconLabel.setIcon(image);
    }

    public void showTableauView(){
        cardLayout.show(layoutPanel,"tableau");
        updateSetEnable(true);
    }
    public void showChannelView(){
        cardLayout.show(layoutPanel,"channel");
        updateSetEnable(false);
    }

    private void updateSetEnable(boolean enable){
        menuBar.getMenu(0).getItem(1).setEnabled(enable);
    }

    private JPanel buildTableauViewHeader(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 15, 15,10));

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0,50));

        tableauHeader = new JLabel(" ");
        tableauHeader.setAlignmentX(Component.LEFT_ALIGNMENT);
        tableauHeader.setFont(new Font("Inter", Font.BOLD, 25));
        textPanel.add(tableauHeader);

        tableauDescription = new NiceTextArea("");
        tableauDescription.setMaximumSize(new Dimension(700, 600));

        textPanel.add(tableauDescription);

        tableauIconLabel = new JLabel();
        panel.add(tableauIconLabel);
        panel.add(textPanel);

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

    public void setChannelListListener(ListSelectionListener listener){
        channelJList.addListSelectionListener(listener);
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

    public void setTableauTableListener(ListSelectionListener listener){
        tableau.getSelectionModel().addListSelectionListener(listener);
    }
}
