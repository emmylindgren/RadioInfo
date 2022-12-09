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

        Document document = builder.parse("http://api.sr.se/api/v2/channels");

        //Normalize the XML Structure
        document.getDocumentElement().normalize();

        //Get all channels from XML.
        NodeList nodeList = document.getElementsByTagName("channel");
        ArrayList<Channel> channels = new ArrayList<>();

        for (int i = 0; i < nodeList.getLength(); i++)
        {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element eElement = (Element) node;

                String name = eElement.getAttribute("name");
                String imageUrl = eElement.getElementsByTagName("image").item(0).getTextContent();
                String tagline = eElement.getElementsByTagName("tagline").item(0).getTextContent();
                String tableauUrl = eElement.getElementsByTagName("scheduleurl").item(0).getTextContent();
                String channelType = eElement.getElementsByTagName("channeltype").item(0).getTextContent();

                channels.add(new Channel(name,imageUrl,tagline,tableauUrl,channelType));
            }
        }
        for (Channel chan:channels) {
            System.out.println(chan);
        }
    }
}
