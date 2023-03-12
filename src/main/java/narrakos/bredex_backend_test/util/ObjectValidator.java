package narrakos.bredex_backend_test.util;

import narrakos.bredex_backend_test.exceptions.ObjectValidationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

public class ObjectValidator {

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static void validateObject(Object object) {
        Set<ConstraintViolation<Object>> violations = validator.validate(object);

        if (violations.isEmpty()) {
            return;
        }

        Set<String> errorMessages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toSet());

        throw new ObjectValidationException(errorMessages);
    }
}
