package se.umu.cs.emli.Model;

import javax.swing.*;
import java.util.ArrayList;
/**
 * Extension of AbstractListModel containing channels. Used with JList in view to display
 * a list of channels, without creating dependencies between view and models.
 * @author Emmy Lindgren, id19eln.
 */
public class ChannelListModel extends AbstractListModel<Channel> {
    ArrayList<Channel> channels;
    public ChannelListModel(){
        this.channels = new ArrayList<>();
    }
    /**
     * Add a channel to the listmodel.
     * @param channel, the channel to be added.
     */
    public void add(Channel channel){
        channels.add(channel);
        fireIntervalAdded(this,getSize()-1,getSize());
    }

    /**
     * Get the size of the listmodel.
     * @return int with the size of the listmodel.
     */
    @Override
    public int getSize() {
        return channels.size();
    }

    /**
     * Get element at specified index.
     * @param index the requested index
     * @return the Channel-object at the requested index.
     */
    @Override
    public Channel getElementAt(int index) {
        return channels.get(index);
    }
}