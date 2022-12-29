package se.umu.cs.emli.View;

import se.umu.cs.emli.Model.Channel;
import javax.swing.*;
import java.awt.*;
/**
 * A liscellrenderer for rendering the channel-buttons in channelview. To be set as default listcellrenderer
 * for JList containing channels. Displays the channels as JButtons with image-icon in center and
 * text underneath.
 * @author Emmy Lindgren, id19eln.
 */
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