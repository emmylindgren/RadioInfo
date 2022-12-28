package se.umu.cs.emli.Controller;
/**
 * Functional interface to be called when ProgramImageWorker is done.
 * @author Emmy Lindgren, id19eln.
 */
@FunctionalInterface
public interface CallbackInterface {
    void callBack();
}