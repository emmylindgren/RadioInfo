package se.umu.cs.emli.View;

import se.umu.cs.emli.Model.Channel;
import javax.swing.*;
import java.awt.*;

public class ChannelRenderer extends JButton implements ListCellRenderer<Channel> {
    @Override
    public Component getListCellRendererComponent(JList<? extends Channel> list, Channel value,
                                                  int index, boolean isSelected, boolean cellHasFocus) {
        setText(value.getName());
        setIcon(value.getSmallerImageIcon());

        setVerticalTextPosition(SwingConstants.BOTTOM);
        setHorizontalTextPosition(SwingConstants.CENTER);
        return this;
    }
}
