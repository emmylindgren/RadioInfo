package se.umu.cs.emli.Model;

import org.w3c.dom.Element;
import javax.swing.*;
import java.awt.*;

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
        this.image = new ImageLoader().loadImage(imageURL);
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

    public boolean hasHashedTableau(){
        return hasHashedTableau;
    }
    public void setHasHashedTableau(boolean hasHashedTableau){
        this.hasHashedTableau = hasHashedTableau;
    }
    public ProgramTableModel getTableau() {
        return tableau;
    }
    public String getTagline(){
        return tagline;
    }
    public ImageIcon getImageIcon(){
        return image;
    }
    public ImageIcon getBiggerImageIcon(){ return new ImageIcon(image.getImage()
            .getScaledInstance(100,100, Image.SCALE_SMOOTH));}
    //TODO: Remove later. Now for debugging.
    @Override
    public String toString() {
        return "Name: "+name+ " image: "+imageURL+" tagline: "+tagline+ " type: "+channelType + " tablauURL: "+tableauURL;
    }
    public String getName(){return name;}
    public String getTableauURL() {
        return tableauURL;
    }
}
