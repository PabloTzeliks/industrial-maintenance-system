package pablo.tzeliks.infraestructure.exception;

public class PersistenceException extends RuntimeException {
    public PersistenceException(String message) {
        super(message);
    }
}