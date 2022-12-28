package se.umu.cs.emli.Model;

import org.w3c.dom.Element;
import javax.swing.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
/**
 * Model class to represent a Program in radioInfo.
 * Loads a program from an XML-element containing program-information.
 * @author Emmy Lindgren, id19eln.
 */
public class Program {
    private String name;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final String description;
    private final String imageURL;
    private Status status;
    private ImageIcon image;

    /**
     * Constructor for Program. Creates a Program from XML-element.
     * @param programElement, the xml-element containing program information.
     */
    public Program(Element programElement){
        this.name = getElementFromTagName(programElement,"title");

        String subTitle = getElementFromTagName(programElement,"subtitle");
        if(subTitle != null) this.name = this.name + " " + subTitle;

        this.startTime = loadLocallyZonedTime(getElementFromTagName(programElement,"starttimeutc"));
        this.endTime = loadLocallyZonedTime(getElementFromTagName(programElement,"endtimeutc"));
        this.description = getElementFromTagName(programElement,"description");
        this.imageURL = getElementFromTagName(programElement,"imageurl");
    }
    /**
     * Loads zoned time from dateTime in string format, and returns it as a local date time.
     * @param dateTimeString, the date in string format.
     * @return the date as a LocalDateTime-object in the timezone of the system.
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
    /**
     * Checks whether the program is ongoing in interval between two times.
     * Also sets the programs status according to right now (ended, ongoing or upcoming).
     * @param start, the start-time of the interval to check.
     * @param end, the end-time of the interval to check.
     * @return boolean saying if program is ongoing in interval or not.
     */
    public boolean isWithinRange(LocalDateTime start, LocalDateTime end){
        LocalDateTime now = LocalDateTime.now();
        if(endTime.isBefore(now)){
            status = Status.ENDED;
        }
        else if(startTime.isAfter(now)){
            status = Status.UPCOMING;
        }
        else {
            status = Status.ONGOING;
        }
        return startTime.isBefore(end) && endTime.isAfter(start);
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }
    public LocalDateTime getEndTime() {
        return endTime;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public Status getStatus(){return status;}
    public ImageIcon getImage(){return image;}
    public void setImage(ImageIcon image){this.image = image;}
    public String getImageURL() {return imageURL;}
}