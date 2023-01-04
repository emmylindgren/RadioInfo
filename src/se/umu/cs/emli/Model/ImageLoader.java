package se.umu.cs.emli.Model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.Objects;
/**
 * Class for loading images from URL. If URL is null or if the image could not be loaded an
 * image-not-found icon is returned instead.
 * Also loads a refresh-icon with a smaller size.
 * @author Emmy Lindgren, id19eln.
 */
public class ImageLoader {
    /**
     * Loads image-icon from URL with specified size.
     * @param imageURL, the URL from which the image is to be loaded.
     * @param width, the requested width for the image-icon.
     * @param height, the requested height for the image-icon.
     * @return the loaded image-icon.
     */
    public ImageIcon loadImageIcon(String imageURL, int width, int height){
        Image img = null;
        boolean loadImageNotFoundIcon = imageURL == null;

        if(!loadImageNotFoundIcon){
            try {
                img = ImageIO.read(new URL(imageURL));
            } catch (IOException e) {
                loadImageNotFoundIcon = true;
            }
        }
        if(loadImageNotFoundIcon){
            try {
                img = ImageIO.read(Channel.class.getResourceAsStream("/image-not-found-icon.png"));
            } catch (IOException| NullPointerException ignored) {
            }
        }
        if(img != null){
            img = img.getScaledInstance(width,height,Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        }
        return null;
    }
    /**
     * Loads refresh-icon from project. Size 20 x 20.
     * @return the loaded refresh-icon.
     */
    public ImageIcon loadRefreshIcon(){
        return loadIcon("/refresh.png");
    }
    /**
     * Loads load-icon from project. Size 20 x 20.
     * @return the loaded load-icon.
     */
    public ImageIcon loadInformationIcon(){
        return loadIcon("/information.png");
    }

    private ImageIcon loadIcon(String src){
        Image img = null;
        try {
            img = ImageIO.read(Objects.requireNonNull(Channel.class.getResourceAsStream(src)));
        } catch (IOException| NullPointerException ignored) {
        }
        if(img !=null){
            img = img.getScaledInstance(20,20,Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        }
        return null;
    }
}