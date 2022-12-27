package se.umu.cs.emli.Controller;

import se.umu.cs.emli.Model.ImageLoader;
import se.umu.cs.emli.Model.Program;
import javax.swing.*;
import java.util.concurrent.ExecutionException;

public class ProgramImageWorker extends SwingWorker<ImageIcon,Object> {
    private Program program;
    private CallbackInterface callbackInterface;

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
