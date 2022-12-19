package se.umu.cs.emli.View;

import javax.swing.*;
import java.awt.*;

/**
 * View-class for making a nicer JTextArea for descriptions and such.
 * Sets the font and style of the JTextArea.
 */
public class NiceTextArea extends JTextArea {
    public NiceTextArea(String content){
        setText(content);
        setFont(new Font("Inter", Font.PLAIN, 15));
        setLineWrap(true);
        setOpaque(false);
        setEditable(false);
        setAlignmentX(Component.LEFT_ALIGNMENT);
        setWrapStyleWord(true);
    }
}