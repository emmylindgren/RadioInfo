package se.umu.cs.emli.View;

import javax.swing.*;
import java.awt.*;
/**
 * Class for making two different form of JDialogs. One for displaying information about a specific program
 * and one for displaying information about RadioInfo-tableauview.
 * @author Emmy Lindgren, id19eln.
 */
public class BuildDialog {
    /**
     * Builds a JDialog that displays information about a specific program.
     * @param frame, the frame of which the JDialog should be appended to.
     * @param programName, the name of the program.
     * @param programDescription, the description of the program.
     * @param status, the time-status of the program.
     * @param icon, the imageIcon corresponding to the program.
     */
    public void buildProgramDialog(JFrame frame, String programName, String programDescription, String status,
                                      ImageIcon icon){
        JDialog d = new JDialog(frame, "Programinformation");
        JPanel panel =(JPanel)d.getContentPane();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20,10));
        d.setLayout(new BorderLayout());
        d.setPreferredSize(new Dimension(450,300));

        NiceTextArea headLine = new NiceTextArea(programName);
        headLine.setFont(new Font("Inter", Font.BOLD, 18));

        NiceTextArea informationText = new NiceTextArea(programDescription);
        NiceTextArea statusText = new NiceTextArea("Status: " + status);
        statusText.setFont(new Font("Inter", Font.ITALIC, 12));

        JScrollPane informationScroll = new JScrollPane(informationText);
        informationScroll.setBorder(BorderFactory.createEmptyBorder(20,0,5,10));
        d.add(informationScroll,BorderLayout.CENTER);
        d.add(new JLabel(icon),BorderLayout.EAST);
        d.add(headLine,BorderLayout.NORTH);
        d.add(statusText, BorderLayout.SOUTH);

        d.pack();
        d.setLocationRelativeTo(frame);
        d.setVisible(true);
    }

    /**
     * Builds a JDialog with information about how the tableauview works.
     * @param frame, the frame of which the JDialog should be appended to.
     */
    public void buildInfoDialog(JFrame frame){
        JDialog d = new JDialog(frame, "Tablåinformation");
        JPanel panel =(JPanel)d.getContentPane();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20,10));
        d.setPreferredSize(new Dimension(470,350));

        NiceTextArea headLine = new NiceTextArea("Information - Tablå");
        headLine.setFont(new Font("Inter", Font.BOLD, 18));
        d.add(headLine,BorderLayout.NORTH);

        NiceTextArea informationText = new NiceTextArea("""
                Här kan du se alla program som går på denna kanal, 6 timmar före hämtningen och 12 timmar efter.
                
                Färgerna på kolumnerna för start- och-sluttid visar om programmet har gått, går just nu eller kommer att gå snart.
                Grått betyder att programmet redan gått, rött att det går just nu och grönt att det kommer att gå.
                
                Dubbelklicka på ett program för att få veta mer om just det programmet.""");
        informationText.setBorder(BorderFactory.createEmptyBorder(20,0,0,10));

        JScrollPane informationScroll = new JScrollPane(informationText);
        informationScroll.setBorder(null);
        d.add(informationScroll,BorderLayout.CENTER);

        d.pack();
        d.setLocationRelativeTo(frame);
        d.setVisible(true);
    }
}