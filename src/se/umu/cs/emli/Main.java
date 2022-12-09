package se.umu.cs.emli;

import se.umu.cs.emli.Controller.Controller;
import se.umu.cs.emli.Model.ApiChannelParser;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        SwingUtilities.invokeLater(Controller::new);
    }
}