package se.umu.cs.emli.Model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ProgramTableModel extends AbstractTableModel {

    //TODO: Implementera denna. Tror den är långt från klar, lyssnare här eller?
    ArrayList<Program> programs;
    public ProgramTableModel(){
        programs = new ArrayList<>();
    }

    public void addChannel(Program program){
        programs.add(program);
        //DENNA va från Johans kod, behövs den? Den uppdaterar lyssnare om att något lagts till i raden.
        //Kan tänka mig att vi i detta program istället skriver en typ updatechannels där
        //man skickar in en hel lista och sätter om den, så typ: this.fireTableDataChanged(); eller liknande?
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
        /* TODO: Detta ska också in så man kan hämta de värdet.
        return switch (columnIndex) {
            case 0 -> program.getName();
            case 1 -> program.getStartTime;
            case 2 -> program.getEndTime;
            default -> null;
        };*/
        return null;
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
