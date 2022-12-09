package se.umu.cs.emli.Model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ChannelList extends AbstractTableModel {

    //TODO: Denna Ska vara för TABLÅN! EJ FÖR CHANNEL. Gjorde lite fel haha.
    //TODO: Bör byta namn då?
    //men frågan är om det ska vara så för channels också?
    ArrayList<Channel> channels;
    public ChannelList(){
        channels = new ArrayList<>();
    }

    public void addChannel(Channel channel){
        channels.add(channel);
    }

    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }


    @Override
    public String getColumnName(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> "Name";
            case 1 -> "Username";
            default -> null;
        };
    }
}
