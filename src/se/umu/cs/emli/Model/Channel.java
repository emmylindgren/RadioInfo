package se.umu.cs.emli.Model;

import java.util.ArrayList;

public class Channel {
    private String name;
    private String imageURL;
    private String tagline;
    private String tableauURL;
    private String channelType;
   // private ArrayList<Program> programs;
    // TODO: Ska de här vara en channellist då? Eller ska de heta programList kanske :) Ska den ens ha koll på sina
    //kanaler?

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
