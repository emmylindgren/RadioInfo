package se.umu.cs.emli.Model;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Program {
    private String name;
    private String startTime;
    private String endTime;
    private String description;
    private String imageURL;

    public Program(Element programElement){
        this.name = getElementFromTagName(programElement,"title");
        String subTitle = getElementFromTagName(programElement,"subtitle");
        if(subTitle != null) this.name = this.name + " - " + subTitle;
        this.startTime = getElementFromTagName(programElement,"starttimeutc");
        this.endTime = getElementFromTagName(programElement,"endtimeutc");
        this.description = getElementFromTagName(programElement,"description");
        this.imageURL = getElementFromTagName(programElement,"imageurl");
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


    //TODO: Remove later. Now for debugging.
    @Override
    public String toString() {
        return "Name: "+name+ " image: "+imageURL+" desc: "+description+ " start: "+startTime
                + " end: "+ endTime;
    }
}
