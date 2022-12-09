package se.umu.cs.emli.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View {
    private JFrame frame;
    private JMenuBar menuBar;

    //TODO: När ska visa upp programtablå så blir de: JTable jTable=new JTable(programList);
    //            JScrollPane sPane=new JScrollPane(jTable);
    public View(String title){
        frame = new JFrame();
        frame.setTitle(title);
        frame.setSize(new Dimension(950, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        this.menuBar = buildJMenuBar();
        frame.setJMenuBar(this.menuBar);
        setChannelView();
    }

    public void setChannelView(){
        //Påbörjad design av förstasidan med första panelen med info!
        JPanel panel = new JPanel();
        JLabel header = new JLabel("Radioinfo Sveriges Radio");

        header.setFont(new Font("Inter", Font.BOLD, 25));

        //TODO: fixa så denna wrappar texten :) och font och placering av text!
        JLabel description = new JLabel();
        description.setMaximumSize(new Dimension(300, 600));
        description.setText("<HTML> Här kan du få information om Svergies Radios kanaler och deras tablåer" +
                ". blabla nåt mer?" +
                "Tryck här nere eller välj kanal uppe i menyn. Lite mer " +
                "hjälp här om hur man använder programmet vore bra! </HTML>");

        panel.add(header);
        panel.add(description);
        frame.add(panel);
    }


    public JMenuBar buildJMenuBar(){
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
