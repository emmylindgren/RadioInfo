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
   // private ArrayList<Program> programs;
    // TODO: Ska de här vara en channellist då? Eller ska de heta programList kanske :) Ska den ens ha koll på sina
    //kanaler?

    public Channel(String name, String imageURL,String tagline,String tableauURL,String channelType){
        this.name = name;
        this.imageURL = imageURL;
        this.tagline = tagline;
        this.tableauURL = tableauURL;
        this.channelType = channelType;
        this.tableau = new ProgramTableModel();
        this.hasHashedTableau = false;
        loadImage();
    }

    public Channel(Element channelElement){

        this.name = channelElement.getAttribute("name");
        this.imageURL = getElementFromTagName(channelElement,"image");
        this.tagline = getElementFromTagName(channelElement,"tagline");
        this.tableauURL = getElementFromTagName(channelElement,"scheduleurl");
        this.channelType = getElementFromTagName(channelElement,"channeltype");
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
        //TODO: Load tableau here first time. Maybe solwe the manual updates here too.
        //Skicka tablaue till worker och denna fyller tableu.
        //Den gör programs. Printa där i så fall :)
        // den ska ta in tablemodel & url! TODO: Hantera här IFALL url == null vilket den verkligen kan va :)
        // Då ska vi väl skriva ut nåt fint att de ej finns. eller typ en inforuta.
        if(tableauURL == null){
            System.out.println("nulligt");
        }
        else{
            ApiTableauParser parser = new ApiTableauParser(tableau,tableauURL);
            try {
                parser.parseInfo();
            } catch (ParserConfigurationException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (SAXException e) {
                throw new RuntimeException(e);
            }
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
