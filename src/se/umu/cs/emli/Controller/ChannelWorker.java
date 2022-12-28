package se.umu.cs.emli.Controller;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import se.umu.cs.emli.Model.Channel;
import se.umu.cs.emli.Model.ChannelListModel;
import se.umu.cs.emli.View.MainView;
import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Swingworker used to parse channel information.
 * Handles exceptions by showing information to the user.
 */
public class ChannelWorker extends SwingWorker<Object, Channel> {
    private final MainView view;
    private final ChannelListModel channelList;

    public ChannelWorker(MainView view, ChannelListModel channelList){
        this.view = view;
        this.channelList = channelList;
    }
    @Override
    protected Object doInBackground() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse("http://api.sr.se/api/v2/channels?pagination=false");
        //Normalize the XML Structure
        document.getDocumentElement().normalize();
        //Get all channels from XML.
        NodeList nodeList = document.getElementsByTagName("channel");

        for (int i = 0; i < nodeList.getLength(); i++)
        {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element channelElement = (Element) node;
                Channel chan = new Channel(channelElement);
                publish(chan);
            }
        }
        return null;
    }
    @Override
    protected void process(List<Channel> chunks) {
        for (Channel chan: chunks) {
            channelList.add(chan);
        }
    }
    @Override
    protected void done(){
        try {
            get();
        } catch (InterruptedException e) {
            view.showInformation("Det gick inte att ladda in kanalerna.");
        } catch (ExecutionException e) {
            view.showInformation("Det gick inte att ladda in kanalerna. Kontrollera din uppkoppling.");
        }
    }
}