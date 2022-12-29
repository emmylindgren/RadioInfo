package se.umu.cs.emli.View;

import se.umu.cs.emli.Model.Program;
import se.umu.cs.emli.Model.ProgramTableModel;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
/**
 * A TableCellRenderer to be set as default TableCellRenderer for JTable-rows displaying tableau-time information.
 * Calls super on DefaultTableCellRenderer and then sets the color of the cell depending on the current
 * program time-status.
 * @author Emmy Lindgren, id19eln.
 */
public class ProgramRenderer extends DefaultTableCellRenderer {
    public ProgramRenderer() {
        super();
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        JLabel component = (JLabel) super.getTableCellRendererComponent(table, value, isSelected,
                hasFocus, row, column);

        ProgramTableModel model = (ProgramTableModel) table.getModel();
        Program program = model.getProgramFromRow(row);

        switch (program.getStatus()) {
            case ENDED -> setBackground(Color.decode("#DFE0E2"));
            case ONGOING -> setBackground(Color.decode("#EDBBB4"));
            case UPCOMING -> setBackground(Color.decode("#C2E1C2"));
        }
        return component;
    }
}