package se.umu.cs.emli.View;

import javax.swing.*;
import java.awt.*;

public class View {
    private JFrame frame;
    private JMenuBar menubar;

    public View(String title){
        frame = new JFrame();
        frame.setTitle(title);
        frame.setSize(new Dimension(950, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        this.menubar = buildJMenuBar();
        frame.setJMenuBar(this.menubar);
    }


    public JMenuBar buildJMenuBar(){
        JMenuBar bar = new JMenuBar();
        JMenu menu   = new JMenu("Meny");
        JMenuItem i1 = new JMenuItem("Alla Kanaler");
        JMenuItem i2 = new JMenuItem("Uppdatera sidan");
        JMenuItem i3 = new JMenuItem("Avsluta");

        menu.add(i1);
        menu.add(i2);
        menu.addSeparator();
        menu.add(i3);
        bar.add(menu);
        return bar;
    }
}
