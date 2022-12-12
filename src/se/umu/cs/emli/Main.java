package se.umu.cs.emli;

import se.umu.cs.emli.Controller.Controller;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Controller::new);
    }
}