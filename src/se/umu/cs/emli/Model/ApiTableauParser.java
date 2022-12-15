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
import java.util.ArrayList;
import java.util.Timer;

public class ApiTableauParser extends Timer {
    private ProgramTableModel tableau;
    private String url;

    public ApiTableauParser(ProgramTableModel tableau, String url){
        this.tableau = tableau;
        this.url = url;
    }

    //TODO: HANDLE EXCEPTIONS, where?
    //TODO: Hantera om 404.. Kanske de är exttt. Asså vissa url kan va fel :)
    public void parseInfo() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(url+"&pagination=false");
        //Normalize the XML Structure
        document.getDocumentElement().normalize();
        //Get all the programs from XML
        NodeList programList = document.getElementsByTagName("scheduledepisode");
        //TODO: Hämta andra datum om det behövs :)

        for (int i = 0; i < programList.getLength(); i++) {
            Node node = programList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element programElement = (Element) node;
                Program program = new Program(programElement);

                System.out.println(program);

            }
        }
    }

}
