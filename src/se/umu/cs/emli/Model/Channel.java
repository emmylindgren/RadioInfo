package se.umu.cs.emli.Model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class Channel {
    private String name;
    private String imageURL;
    private String tagline;
    private String tableauURL;
    private String channelType;
    private ImageIcon image;
   // private ArrayList<Program> programs;
    // TODO: Ska de här vara en channellist då? Eller ska de heta programList kanske :) Ska den ens ha koll på sina
    //kanaler?

    public Channel(String name, String imageURL,String tagline,String tableauURL,String channelType){
        this.name = name;
        this.imageURL = imageURL;
        this.tagline = tagline;
        this.tableauURL = tableauURL;
        this.channelType = channelType;
        loadImage();
    }

    /**
     * Loads the channels image.
     * TODO: Maybe move this? So could be used by both channels and program.
     */
    private void loadImage(){
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
                img = ImageIO.read(Channel.class.getResource("../resources/image-not-found-icon.png"));
            } catch (IOException| NullPointerException ignored) {
            }
        }
        if(img != null){
            img = img.getScaledInstance(60,60,Image.SCALE_SMOOTH);
            image = new ImageIcon(img);
        }
    }

    public ImageIcon getImageIcon(){
        return image;
    }
    //TODO: Remove later. Now for debugging.
    @Override
    public String toString() {
        return "Name: "+name+ " image: "+imageURL+" tagline: "+tagline+ " type: "+channelType;
    }

    public String getName(){return name;}
}
