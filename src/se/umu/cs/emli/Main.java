package se.umu.cs.emli;

import se.umu.cs.emli.Controller.Controller;
import javax.swing.*;
/**
 * Main-class for RadioInfo-program.
 * Starting a controller on the EDT.
 * @author Emmy Lindgren, id19eln.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Controller::new);
    }
}