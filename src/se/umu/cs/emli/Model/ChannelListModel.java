package se.umu.cs.emli.Model;

import javax.swing.*;
import java.util.ArrayList;

public class ChannelListModel extends AbstractListModel {
    ArrayList<Channel> channels;
    public ChannelListModel(ArrayList<Channel> channels){
        this.channels = channels;
    }
    @Override
    public int getSize() {
        return channels.size();
    }

    @Override
    public Object getElementAt(int index) {
        return channels.get(index);
    }


}
