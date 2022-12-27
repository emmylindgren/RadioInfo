package se.umu.cs.emli.View;

import se.umu.cs.emli.Model.Program;
import se.umu.cs.emli.Model.ProgramTableModel;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ProgramRenderer extends JLabel implements TableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        ProgramTableModel model = (ProgramTableModel) table.getModel();
        Program program = model.getProgramFromRow(row);
        System.out.println("hej");
        System.out.println(program);
        switch (program.getStatus()){
            case ENDED -> super.setBackground(Color.GREEN);
            case ONGOING -> super.setBackground(Color.black);
            case UPCOMING -> super.setBackground(Color.blue);
        }
        return this;
    }
}
