package se.umu.cs.emli.Model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class ApiChannelParser extends SwingWorker<Object,Object> {
    @Override
    protected Object doInBackground() throws Exception {
        loadChannels();
        return null;
    }

    private void loadChannels() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse("http://api.sr.se/api/v2/channels?pagination=false");

        //Normalize the XML Structure
        document.getDocumentElement().normalize();

        //Get all channels from XML.
        NodeList nodeList = document.getElementsByTagName("channel");
        ArrayList<Channel> channels = new ArrayList<>();
        System.out.println(nodeList.getLength());
        for (int i = 0; i < nodeList.getLength(); i++)
        {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element eElement = (Element) node;

                String name = eElement.getAttribute("name");
                String imageUrl = getElementFromTagName(eElement,"image");
                String tagline = getElementFromTagName(eElement,"tagline");
                String tableauUrl = getElementFromTagName(eElement,"scheduleurl");
                String channelType = getElementFromTagName(eElement,"channeltype");

                channels.add(new Channel(name,imageUrl,tagline,tableauUrl,channelType));
            }
        }

        int i = 1;
        for (Channel chan:channels) {
            System.out.println(i + " : "+chan);
            i++;
        }
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
}
