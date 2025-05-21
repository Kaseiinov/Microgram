package kg.attractor.microgram.exceptions;

public class SuchEmailAlreadyExistsException extends Exception {
    public SuchEmailAlreadyExistsException() {
        super("Such email already exists");
    }
}
