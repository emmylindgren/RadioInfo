package se.umu.cs.emli.Model;

import org.w3c.dom.Element;
import javax.swing.*;
import java.awt.*;
/**
 * Model class to represent a Channel in radioInfo.
 * Loads a channel from an XML-element containing channel-information.
 * Keeps track of channel-information and also channel-tableau, if any tableau is loaded.
 * @author Emmy Lindgren, id19eln.
 */
public class Channel {
    private final String name;
    private final String tagline;
    private final String tableauURL;
    private final ImageIcon image;
    private final ProgramTableModel tableau;
    private boolean hasCachedTableau;

    public Channel(Element channelElement){

        this.name = channelElement.getAttribute("name");
        this.tagline = getElementFromTagName(channelElement,"tagline");
        this.tableauURL = getElementFromTagName(channelElement,"scheduleurl");
        this.tableau = new ProgramTableModel();
        this.hasCachedTableau = false;

        String imageURL = getElementFromTagName(channelElement, "image");
        this.image = new ImageLoader().loadImageIcon(imageURL,100,100);
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
    public boolean hasCachedTableau(){
        return hasCachedTableau;
    }
    public void setHasCachedTableau(boolean hasCachedTableau){
        this.hasCachedTableau = hasCachedTableau;
    }
    public ProgramTableModel getTableau() {
        return tableau;
    }
    public String getTagline(){
        return tagline;
    }
    public ImageIcon getSmallerImageIcon(){
        return new ImageIcon(image.getImage()
                .getScaledInstance(60,60, Image.SCALE_SMOOTH));
    }
    public ImageIcon getBiggerImageIcon(){ return image;}
    public String getName(){return name;}
    public String getTableauURL() {
        return tableauURL;
    }
}