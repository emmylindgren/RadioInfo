package se.umu.cs.emli.View;

import se.umu.cs.emli.Model.*;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
/**
 * Main view-class for the program RadioInfo.
 * Builds the views and components for the program and puts it all together.
 * @author Emmy Lindgren, id19eln.
 */
public class MainView {
    private JFrame frame;
    private JMenuBar menuBar;
    private final JList<Channel> channelJList;
    private final CardLayout cardLayout;
    private final JPanel layoutPanel;
    private JLabel tableauHeader;
    private JTextArea tableauDescription;
    private JLabel tableauIconLabel;
    private JTable tableau;
    private JButton updateButton;
    /**
     * Builds channel- and tableau-view, and sets the channel-view to be shown.
     * @param channelList, the JListModel containing channels to be displayed in the view.
     */
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
    private void buildFrameWithMenuBar() {
        frame = new JFrame();
        frame.setTitle("RadioInfo");
        frame.setSize(new Dimension(950, 600));
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
    private JPanel buildTableau(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 15, 15,10));

        panel.add(buildTableauInfoPanel());

        tableau = new JTable(new ProgramTableModel());
        tableau.setRowHeight(30);
        tableau.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableau.setRowSelectionAllowed(true);
        tableau.setColumnSelectionAllowed(false);

        JScrollPane scroll = new JScrollPane(tableau,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scroll,BorderLayout.CENTER);

        return panel;
    }
    private JPanel buildTableauInfoPanel() {
        JPanel tableauInfo = new JPanel();
        tableauInfo.setLayout(new BorderLayout());
        tableauInfo.setBorder(BorderFactory.createEmptyBorder(0, 0, 5,0));

        JLabel header = new JLabel("Kanaltablå");
        header.setAlignmentX(Component.LEFT_ALIGNMENT);
        header.setFont(new Font("Inter", Font.BOLD, 25));

        JPanel buttons = new JPanel();
        ImageLoader loader = new ImageLoader();
        updateButton = new JButton();
        updateButton.setIcon(loader.loadRefreshIcon());

        JButton informationButton = new JButton();
        informationButton.addActionListener(e -> new BuildDialog().buildInfoDialog(frame));
        informationButton.setIcon(loader.loadInformationIcon());

        buttons.add(informationButton);
        buttons.add(updateButton);

        tableauInfo.add(header,BorderLayout.CENTER);
        tableauInfo.add(buttons,BorderLayout.EAST);
        return tableauInfo;
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
     * Sets the tableau-view to be shown in GUI.
     */
    public void showTableauView(){
        cardLayout.show(layoutPanel,"tableau");
        enableMenuItemUpdate(true);
    }
    /**
     * Sets the channel-view to be shown in GUI.
     */
    public void showChannelView(){
        cardLayout.show(layoutPanel,"channel");
        enableMenuItemUpdate(false);
    }
    /**
     * Sets the current ProgramTableModel for JTable showing the tableau.
     * @param model, the programTableModel to be set.
     */
    public void setTableau(ProgramTableModel model){
        tableau.setModel(model);

        TableColumnModel columnModel = tableau.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(250);
        for (int i = 1; i < 3; i++) {
            columnModel.getColumn(i).setPreferredWidth(20);
            columnModel.getColumn(i).setCellRenderer(new ProgramRenderer());
        }
    }
    /**
     * Sets the tableauheader-information in tableau-view.
     * @param name, the name of the Channel of which tableau is showing.
     * @param tagline, the tagline of the Channel of which tableau is showing.
     * @param image, the ImageIcon of the Channel of which tableau is showing.
     */
    public void setTableauInfo(String name,String tagline,ImageIcon image){
        tableauHeader.setText(name);
        tableauDescription.setText(tagline);
        tableauIconLabel.setIcon(image);
    }
    private void enableMenuItemUpdate(boolean enable){
        menuBar.getMenu(0).getItem(1).setEnabled(enable);
    }
    /**
     * Displays information about a specific program.
     * @param programName, the name of the program of which is to be displayed.
     * @param programDescription, the description of the program of which is to be displayed.
     * @param status, the time-status of the program of which is to be displayed.
     * @param icon, the ImageIcon of the program of which is to be displayed.
     */
    public void showProgramInfo(String programName, String programDescription, String status, ImageIcon icon){
        new BuildDialog().buildProgramDialog(frame,programName,programDescription,status,icon);
    }
    /**
     * Sets or unsets wait-cursor for the program.
     * @param waitCursor, boolean saying if the wait-cursor should be active or not.
     */
    public void setWaitCursor(boolean waitCursor){
        if(waitCursor) frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        else frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
    /**
     * Shows information to the user via the GUI, in a dialogbox with option OK.
     * @param info, a string containing the information to be shown to the user.
     */
    public void showInformation(String info){
        Object[] options = {"OK"};
        JOptionPane.showOptionDialog(null, info, "Information",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);
    }
    /**
     * Sets actionListener for the ChannelList.
     * @param listener, the listener to set.
     */
    public void setChannelListListener(ListSelectionListener listener){
        channelJList.addListSelectionListener(listener);
    }
    public void setTableauTableListener(MouseAdapter listener){
        tableau.addMouseListener(listener);
    }
    /**
     * Sets actionListener for first menu item in the menu.
     * @param actionListener = the listener to set.
     */
    public void setChannelMenuItemListener(ActionListener actionListener) {
        menuBar.getMenu(0).getItem(0).addActionListener(actionListener);
    }
    /**
     * Replaces actionListener for second menu item in the menu. If there is a listener on the
     * menu item already it is replaced by the new one. If not, the new one is set.
     * @param actionListener, the listener to set.
     */
    public void replaceUpdateMenuItemListener(ActionListener actionListener) {
        if(menuBar.getMenu(0).getItem(1).getActionListeners().length > 0){
            menuBar.getMenu(0).getItem(1)
                    .removeActionListener(menuBar.getMenu(0).getItem(1).getActionListeners()[0]);
        }
        menuBar.getMenu(0).getItem(1).addActionListener(actionListener);
    }
    /**
     * Sets actionListener for third menu item in the menu.
     * Separators excluded in indexing of menu.
     * @param actionListener, the listener to set.
     */
    public void setCancelItemListener(ActionListener actionListener) {
        menuBar.getMenu(0).getItem(3).addActionListener(actionListener);
    }

    /**
     * Replaces the actionListener for the update-button in tableau-view. If there is a listener on the
     * update-button already it is replaced by the new one. If not, the new one is set.
     * @param actionListener, the listener to set
     */
    public void replaceUpdateButtonListener(ActionListener actionListener){
        if(updateButton.getActionListeners().length > 0){
            updateButton.removeActionListener(updateButton.getActionListeners()[0]);
        }
        updateButton.addActionListener(actionListener);
    }
}