package se.umu.cs.emli.Model;

import javax.swing.*;
import java.util.ArrayList;

public class ChannelListModel extends AbstractListModel<Channel> {
    ArrayList<Channel> channels;
    public ChannelListModel(){
        this.channels = new ArrayList<>();
    }

    public void add(Channel channel){
        channels.add(channel);
        fireIntervalAdded(this,getSize()-1,getSize());
    }
    @Override
    public int getSize() {
        return channels.size();
    }

    @Override
    public Channel getElementAt(int index) {
        return channels.get(index);
    }
}