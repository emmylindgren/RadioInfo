package se.umu.cs.emli.Model;

import org.w3c.dom.Element;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Program {
    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String description;
    private String imageURL;


    public Program(Element programElement){
        this.name = getElementFromTagName(programElement,"title");
        String subTitle = getElementFromTagName(programElement,"subtitle");
        if(subTitle != null) this.name = this.name + " - " + subTitle;
        this.startTime = loadLocallyZonedTime(getElementFromTagName(programElement,"starttimeutc"));
        this.endTime = loadLocallyZonedTime(getElementFromTagName(programElement,"endtimeutc"));
        this.description = getElementFromTagName(programElement,"description");
        this.imageURL = getElementFromTagName(programElement,"imageurl");
    }

    /**
     * Loads zoned time from dateTime in string format.
     * @param dateTimeString, the date in string format.
     * @return the date as a ZonedDateTime-object in the timezone of the system.
     */
    private LocalDateTime loadLocallyZonedTime(String dateTimeString){
        if(dateTimeString == null) return null;
        ZonedDateTime dateTime = ZonedDateTime.parse(dateTimeString);
        dateTime = ZonedDateTime.ofInstant(dateTime.toInstant(), ZoneId.systemDefault());
        return dateTime.toLocalDateTime();
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

    public boolean isWithinRange(LocalDateTime start, LocalDateTime end){
        return startTime.isBefore(end) && startTime.isAfter(start);
    }


    //TODO: Remove later. Now for debugging.
    @Override
    public String toString() {
        return "Name: "+name+ " image: "+imageURL+" desc: "+description+ " start: "+startTime.toString()
                + " end: "+ endTime.toString();
    }
}
