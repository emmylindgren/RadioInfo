package se.umu.cs.emli.Controller;

import se.umu.cs.emli.Model.ImageLoader;
import se.umu.cs.emli.Model.Program;
import javax.swing.*;
import java.util.concurrent.ExecutionException;

/**
 * SwingWorker for loading images for programs. Loads and sets the image of the program, then calls
 * the callback-method given in constructor when done.
 * @author Emmy Lindgren, id19eln.
 */
public class ProgramImageWorker extends SwingWorker<ImageIcon,Object> {
    private final Program program;
    private final CallbackInterface callbackInterface;
    /**
     * Constructor for ProgramImageWorker.
     * @param program, the program of which to load image.
     * @param callbackInterface, a method to call when done.
     */
    public ProgramImageWorker(Program program, CallbackInterface callbackInterface){
        this.program = program;
        this.callbackInterface = callbackInterface;
    }
    @Override
    protected ImageIcon doInBackground() {
        ImageLoader loader = new ImageLoader();
        return loader.loadImageIcon(program.getImageURL(),130,130);
    }
    @Override
    protected void done() {
        try {
            program.setImage(get());
            callbackInterface.callBack();
        } catch (InterruptedException| ExecutionException e) {
            e.printStackTrace();
        }
    }
}
