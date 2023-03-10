package narrakos.bredex_backend_test.exceptions;

//TODO better name
public class AppConstraintViolationException extends RuntimeException{
    public AppConstraintViolationException(String messages) {
        super(messages);
    }
}
