package narrakos.bredex_backend_test.exceptions;

import java.util.List;

//TODO better name
public class AppConstraintViolationException extends RuntimeException{
    public AppConstraintViolationException(String messages) {
        super(messages);
    }
}
