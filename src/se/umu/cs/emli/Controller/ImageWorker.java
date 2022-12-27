package se.umu.cs.emli.Controller;

import se.umu.cs.emli.Model.ImageLoader;
import se.umu.cs.emli.Model.Program;

import javax.swing.*;
import java.util.concurrent.ExecutionException;

public class ImageWorker extends SwingWorker<ImageIcon,Object> {
    private Program program;
    private CallbackInterface callbackInterface;

    public ImageWorker(Program program, CallbackInterface callbackInterface){
        this.program = program;
        this.callbackInterface = callbackInterface;
    }
    @Override
    protected ImageIcon doInBackground() {
        ImageLoader loader = new ImageLoader();
        return loader.loadImage(program.getImageURL());
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
