package se.umu.cs.emli.Model;

import javax.swing.table.AbstractTableModel;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

//TODO: Använd modellens status på något sätt! Typ färg på tabellen men de kanske måste göras i en
// cell renderer eller så? :)
public class ProgramTableModel extends AbstractTableModel {
    ArrayList<Program> programs;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, dd MMM HH:mm");
    public ProgramTableModel(){
        programs = new ArrayList<>();
    }

    public void addChannel(Program program){
        programs.add(program);
        this.fireTableRowsInserted(programs.size(), programs.size());
    }

    @Override
    public int getRowCount() {
        return programs.size();
    }

    /**
     * @return the number of columns in table.
     */
    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Program program = programs.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> program.getName();
            case 1 -> program.getStartTime().format(formatter);
            case 2 -> program.getEndTime().format(formatter);
            default -> null;
        };
    }

    @Override
    public String getColumnName(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> "Program";
            case 1 -> "Starttid";
            case 2 -> "Sluttid";
            default -> null;
        };
    }
}
