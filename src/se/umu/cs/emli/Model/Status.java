package se.umu.cs.emli.Model;
/**
 * Enum-class to keep time-status for programs.
 * @author Emmy Lindgren, id19eln.
 */
public enum Status {
    ENDED("Avslutat"), UPCOMING("Kommande"), ONGOING("Pågående");
    private final String string;
    Status(String string){this.string = string;}
    @Override
    public String toString() {
        return string;
    }
}