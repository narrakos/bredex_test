package narrakos.bredex_backend_test.exceptions;

import java.util.Set;

public class ObjectValidationException extends RuntimeException {

    private final Set<String> validationErrorMessages;

    public ObjectValidationException(Set<String> validationErrorMessages) {
        this.validationErrorMessages = validationErrorMessages;
    }

    public Set<String> getValidationErrorMessages() {
        return validationErrorMessages;
    }
}
