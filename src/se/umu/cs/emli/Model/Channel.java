package se.umu.cs.emli.Model;

import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
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
    private ProgramTableModel tableau;
    private boolean hasHashedTableau;

    public Channel(Element channelElement){

        this.name = channelElement.getAttribute("name");
        this.imageURL = getElementFromTagName(channelElement,"image");
        this.tagline = getElementFromTagName(channelElement,"tagline");
        this.tableauURL = getElementFromTagName(channelElement,"scheduleurl");
        this.channelType = getElementFromTagName(channelElement,"channeltype");
        this.tableau = new ProgramTableModel();
        this.hasHashedTableau = false;
        loadImage();
    }

    /**
     * Method to get value of tagName from element. Checks if the tagName exists, if it does the value
     * in form of a string is returned, otherwise null is returned.
     * @param e, the element node.
     * @param tagName, the tagName which is connected to the value.
     * @return the value in form of a string.
     */
    private String getElementFromTagName(Element e, String tagName){
        if(e.getElementsByTagName(tagName).getLength() > 0){
            return e.getElementsByTagName(tagName).item(0).getTextContent();
        }
        return null;
    }

    /**
     * Loads the channels image.
     * TODO: Maybe move this? So could be used by both channels and program.
     * TODO: Handle exceptions when loading images.
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

    public ProgramTableModel getTableau() {
        if(!hasHashedTableau){
            loadTableau();
        }
        return tableau;
    }

    private void loadTableau(){
        //TODO: Load tableau here first time. Maybe solwe the manual updates here too?
        if(tableauURL == null){
            //TODO: Fix nice prompt when URL is not found.
            System.out.println("nulligt");
        }
        else{
            //Skicka tablaue till worker och denna fyller tableu.
            ApiTableauParser parser = new ApiTableauParser(tableau,tableauURL);
            parser.loadTableau();
            hasHashedTableau = true;
        }
    }

    public ImageIcon getImageIcon(){
        return image;
    }
    //TODO: Remove later. Now for debugging.
    @Override
    public String toString() {
        return "Name: "+name+ " image: "+imageURL+" tagline: "+tagline+ " type: "+channelType + " tablauURL: "+tableauURL;
    }
    public String getName(){return name;}
}
