package narrakos.bredex_backend_test.error;

import narrakos.bredex_backend_test.exceptions.AppConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(field -> field.getField() + ", " + field.getDefaultMessage())
                .collect(Collectors.toList());

        List<String> globalErrors = ex.getBindingResult()
                .getGlobalErrors()
                .stream()
                .map(field -> field.getObjectName() + ", " + field.getDefaultMessage())
                .collect(Collectors.toList());

        List<String> errors = new ArrayList<>();
        errors.addAll(globalErrors);
        errors.addAll(fieldErrors);

        ApiError err = new ApiError(LocalDateTime.now(),
                "Invalid arguments",
                errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(AppConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(Exception e, WebRequest request) {
        ApiError error = new ApiError(LocalDateTime.now(),
                "Constraint Violation",
                Collections.singletonList(e.getMessage())
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
