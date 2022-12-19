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
 */
public class ImageLoader {
    private final String imageNotFoundSrc = "../resources/image-not-found-icon.png";

    public ImageIcon loadImage(String imageURL){
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
            img = img.getScaledInstance(60,60,Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        }
        return null;
    }
}