package se.umu.cs.emli.View;

import javax.swing.*;
import java.awt.*;
/**
 * View-class for making a JTextArea for descriptions and such.
 * Sets the font and style of the JTextArea.
 * @author Emmy Lindgren, id19eln.
 */
public class NiceTextArea extends JTextArea {
    /**
     * Makes a NiceTextArea displaying text-content with different font and size.
     * Sets line-wrap, non-editable, and aligns the text to the left.
     * @param content, a string containing the content to be displayed in NiceTextArea.
     */
    public NiceTextArea(String content){
        setText(content);
        setFont(new Font("Inter", Font.PLAIN, 15));
        setLineWrap(true);
        setWrapStyleWord(true);
        setOpaque(false);
        setEditable(false);
        setAlignmentX(Component.LEFT_ALIGNMENT);
    }
}