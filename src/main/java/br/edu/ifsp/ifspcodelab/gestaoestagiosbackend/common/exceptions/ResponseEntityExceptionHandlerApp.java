package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ResponseEntityExceptionHandlerApp extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(
            new ProblemDetail("Your JSON format is not valid", Collections.emptyList()), HttpStatus.BAD_REQUEST
        );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex,
        HttpHeaders headers,
        HttpStatus status,
        WebRequest request
    ) {
        List<Violation> violations = ex.getFieldErrors().stream()
            .map(field -> new Violation(field.getField(), field.getDefaultMessage()))
            .collect(Collectors.toList());

        return new ResponseEntity<>(
            new ProblemDetail("Your request parameters didn't validate", violations), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ProblemDetail> resourceNotFound(ResourceNotFoundException exception) {
        return new ResponseEntity<>(
            new ProblemDetail(
                exception.getResourceName().getName() + " not found with id " + exception.getResourceId(),
                Collections.emptyList()
            ),
            HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ProblemDetail> resourceAlreadyExists(ResourceAlreadyExistsException exception) {
        return new ResponseEntity<>(
            new ProblemDetail(
                exception.getResourceName().getName() +
                    " already exists with " +
                    exception.getResourceField() +
                    " " + exception.getResourceValue(),
                List.of(new Violation(exception.getResourceField(), exception.getResourceValue().toString()))
            ),
            HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(ResourceAlreadyExistsTwoFieldsException.class)
    public ResponseEntity<ProblemDetail> resourceAlreadyExistsTwoFields(ResourceAlreadyExistsTwoFieldsException exception) {
        return new ResponseEntity<>(
            new ProblemDetail(
                exception.getResourceName().getName() +
                    " already exists with " +
                    exception.getFirstResourceField() +
                    " " + exception.getFirstResourceValue() +
                    " for " + exception.getSecondResourceField() +
                    " " + exception.getSecondResourceValue(),
                Collections.emptyList()
            ),
            HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(ResourceReferentialIntegrityException.class)
    public ResponseEntity<ProblemDetail> resourceReferentialIntegrity(ResourceReferentialIntegrityException exception) {
        return new ResponseEntity<>(
            new ProblemDetail(
                "Referential integrity exception",
                List.of(
                    new Violation(exception.getPrimary().getName(), "Primary resource"),
                    new Violation(exception.getRelated().getName(), "Related resource")
                )
            ),
            HttpStatus.CONFLICT
        );
    }
}
