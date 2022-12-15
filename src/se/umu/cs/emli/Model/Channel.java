package se.umu.cs.emli.Model;

import org.w3c.dom.Element;
import javax.swing.*;

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
        // Förslag:
        /*
        Används swingtimer för att starta en swingworker som updaterar GUI (eller ja modellen).
        När starta: kolla om timer finns ; om den finns så stäng av & och skapa en ny. Dock ej trådsäkert :p
         */
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
