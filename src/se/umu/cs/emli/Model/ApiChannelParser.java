package se.umu.cs.emli.Model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import se.umu.cs.emli.View.View;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ApiChannelParser extends SwingWorker<ArrayList<Channel>,Channel> {
    //TODO: Som det ser ut just nu är detta en modell och FÅR EJ uppdatera view! Fixa på nåt vis!
    private View view;
    private ChannelListModel channelList;
    public ApiChannelParser(View view, ChannelListModel channelList){
        this.view = view;
        this.channelList = channelList;
    }
    @Override
    protected ArrayList<Channel> doInBackground() throws Exception {
        return loadChannels();
       // return null;
    }

    //TODO: Remove done function? Iaf så att den inte returnerar en lista vi sedan ej använder :)

    @Override
    protected void done(){
        /*
        try {
            view.setChannelList(new ChannelListModel(get()));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }*/
    }
//TODO: HANDLE EXCEPTIONS!
    private ArrayList<Channel> loadChannels() throws ParserConfigurationException, IOException, SAXException {
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
                Element channelElement = (Element) node;

                Channel chan = new Channel(channelElement);
                channels.add(chan);
                publish(chan);
            }
        }
        //TODO: Remove these prints, for testing purposes :)

        int i = 1;
        for (Channel chan:channels) {
            System.out.println(i + " : "+chan);
            i++;
        }
        return channels;
    }


    @Override
    protected void process(List<Channel> chunks) {
        for (Channel chan: chunks) {
            channelList.add(chan);
        }
    }
}
