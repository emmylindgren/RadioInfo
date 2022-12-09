package se.umu.cs.emli.Model;

import java.util.ArrayList;

public class Channel {
    /**
     * Kanal? (namn ska visas i menuitem, men kanske en panel innan jtable som visar
     * bilden på kanalen, tagline, länk till live audio?): Namn, id?, bild, tagline, live audio
     * , channeltype. Visa denna som info i en JPanel ovanför kanaltablå.
     */
    private String name;
    private String imageURL;
    private String tagline;
    private String tableauURL;
    private String channelType;
   // private ArrayList<Program> programs;
    // TODO: Ska de här vara en channellist då? Eller ska de heta programList kanske :)

    public Channel(String name, String imageURL,String tagline,String tableauURL,String channelType){
        this.name = name;
        this.imageURL = imageURL;
        this.tagline = tagline;
        this.tableauURL = tableauURL;
        this.channelType = channelType;
    }

    //TODO: Remove later. Now for debugging.
    @Override
    public String toString() {
        return "Name: "+name+ " image: "+imageURL+" tagline: "+tagline+ " type: "+channelType;
    }
}
