package se.umu.cs.emli.Model;
public enum Status {
    ENDED("Avslutat"), UPCOMING("Kommande"), ONGOING("Pågående");
    private String string;
    Status(String string){this.string = string;}
    @Override
    public String toString() {
        return string;
    }
}