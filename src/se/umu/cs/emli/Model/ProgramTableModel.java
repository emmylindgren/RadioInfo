package se.umu.cs.emli.Model;

import javax.swing.table.AbstractTableModel;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
/**
 * Extension of AbstractTableModel containing programs. Used with JTable in view to display
 * a table of programs in tableau, without creating dependencies between view and models. Specifies colum-titles and
 * how table displays information from programs.
 * @author Emmy Lindgren, id19eln.
 */
public class ProgramTableModel extends AbstractTableModel {
    ArrayList<Program> programs;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, dd MMM HH:mm");
    public ProgramTableModel(){
        programs = new ArrayList<>();
    }
    /**
     * Sets the tableau of programs to the Tablemodel.
     * @param programs, a list of programs which represent the tableau.
     */
    public void setTableau(ArrayList<Program> programs){
        this.programs = programs;
        this.fireTableDataChanged();
    }
    /**
     * @return the number of rows in table.
     */
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
    /**
     * Returns the value at specified position.
     * @param rowIndex, the row whose value is to be queried
     * @param columnIndex, the column whose value is to be queried
     * @return the object at specified position in table.
     */
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
    /**
     * Returns the program from specified row in table.
     * @param rowIndex, the specified row.
     * @return the Program from specified row.
     */
    public Program getProgramFromRow(int rowIndex){return programs.get(rowIndex);}
    /**
     * Gets the columTitle for specified column.
     * @param columnIndex  the column being queried
     * @return a String containing the title of the specified column.
     */
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