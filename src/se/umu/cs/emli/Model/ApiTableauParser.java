package se.umu.cs.emli.Model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Model used for parsing tableau-information from tableau-url.
 * Loads all programs 6 hours back in time and 12 hours fourth.
 * @author Emmy Lindgren, id19eln.
 */
public class ApiTableauParser{
    private final ArrayList<Program> tableauList;
    private final String url;

    public ApiTableauParser(String url){
        this.url = url;
        this.tableauList = new ArrayList<>();
    }

    public ArrayList<Program> loadTableau() throws ParserConfigurationException, IOException, SAXException {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = now.minusHours(6);
        LocalDateTime end = now.plusHours(12);

        int hour = now.getHour();

        if(hour < 6){
            parseProgramsFromDate(now.minusDays(1),start,end);
            parseProgramsFromDate(now,start,end);
        }
        else if(hour > 12 || (hour == 12 && now.getMinute() > 0)){
            parseProgramsFromDate(now,start,end);
            parseProgramsFromDate(now.plusDays(1),start,end);
        }
        else{
            parseProgramsFromDate(now,start,end);
        }

        return tableauList;
    }
    private void parseProgramsFromDate(LocalDateTime dateToGet, LocalDateTime start, LocalDateTime end) throws
            ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(url+"&pagination=false"+"&date="+dateToGet);
        document.getDocumentElement().normalize();

        NodeList programList = document.getElementsByTagName("scheduledepisode");

        for (int i = 0; i < programList.getLength(); i++) {
            Node node = programList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element programElement = (Element) node;
                Program program = new Program(programElement);
                if(program.isWithinRange(start,end)){
                    tableauList.add(program);
                }
            }
        }
    }
}
