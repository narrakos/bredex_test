package narrakos.bredex_backend_test.exceptions;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException() {
        super("Provided token is invalid");
    }
}
