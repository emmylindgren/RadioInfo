package se.umu.cs.emli.Model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

/**
 * Class for loading images from URL. If URL is null or if the image could not be loaded an
 * image-not-found icon is returned instead.
 * Also loads a refresh-icon with a smaller size.
 */
public class ImageLoader {
    private final String imageNotFoundSrc = "../resources/image-not-found-icon.png";
    private final String refreshIconSrc = "../resources/refresh.png";

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
                img = ImageIO.read(Objects.requireNonNull(Channel.class.getResource(imageNotFoundSrc)));
            } catch (IOException| NullPointerException ignored) {
            }
        }
        if(img != null){
            img = img.getScaledInstance(width,height,Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        }
        return null;
    }

    public ImageIcon loadRefreshIcon(){
        Image img = null;
        try {
            img = ImageIO.read(Objects.requireNonNull(Channel.class.getResource(refreshIconSrc)));
        } catch (IOException| NullPointerException ignored) {
        }
        if(img !=null){
            img = img.getScaledInstance(20,20,Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        }
        return null;
    }
}
