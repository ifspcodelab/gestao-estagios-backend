package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @ExceptionHandler(ResourcesNotFoundException.class)
    public ResponseEntity<ProblemDetail> resourcesNotFound(ResourcesNotFoundException exception) {
        return new ResponseEntity<>(
            new ProblemDetail(
                exception.getResourceName().getName() + " not found with ids " + exception.getResourcesIds(),
                Collections.emptyList()
            ),
            HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ProblemDetail> userNotFound(UsernameNotFoundException exception) {
        return new ResponseEntity<>(
            new ProblemDetail(
                exception.getMessage(),
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

    @ExceptionHandler(FileExtensionException.class)
    public ResponseEntity<ProblemDetail> fileExtension(FileExtensionException exception) {
        return new ResponseEntity<>(
            new ProblemDetail(
                "File extension exception",
                List.of(
                    new Violation(exception.getExpectedFileExtension(), "Expected"),
                    new Violation(exception.getActualFileExtension(), "Got")
                )
            ),
            HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(DateIntervalException.class)
    public ResponseEntity<ProblemDetail> dateInterval(DateIntervalException exception) {
        return new ResponseEntity<>(
            new ProblemDetail(
            "interval of days between " +
                exception.getStartDate().toString() +
                " and " +
                exception.getEndDate() +
                " exceeds " +
                exception.getInterval().toString()
                + " days",
                Collections.emptyList()
            ),
            HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(FileMaxSizeException.class)
    public ResponseEntity<ProblemDetail> fileMaxSize(FileMaxSizeException exception) {
        return new ResponseEntity<>(
            new ProblemDetail(
                "File size: " +
                    exception.getActualSize().toString() +
                    "Bs exceeds max size: " +
                    exception.getMaxSize().toString() +
                    "Bs",
                Collections.emptyList()
            ),
            HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(DraftDateSubmissionException.class)
    public ResponseEntity<ProblemDetail> draftDateSubmission(DraftDateSubmissionException exception) {
        return new ResponseEntity<>(
            new ProblemDetail(
                "Cannot send draft for: " +
                    exception.getMonthlyReportDate().toString() +
                    " Because the current date is: " +
                    exception.getActualDate().toString(),
                Collections.emptyList()
            ),
            HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(SubmissionException.class)
    public ResponseEntity<ProblemDetail> finalSubmission(SubmissionException exception) {
        return new ResponseEntity<>(
            new ProblemDetail(
                "Cannot send monthly report because status is: " +
                    exception.getStatus().getName(),
                Collections.emptyList()
            ),
            HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(InternshipWithoutRealizationTermAcceptedException.class)
    public ResponseEntity<ProblemDetail> internshipWithoutRealizationTermAccepted(
            InternshipWithoutRealizationTermAcceptedException exception) {
        return new ResponseEntity<>(
                new ProblemDetail(
                        "Internship with id " + exception.getInternshipId() +
                                " has not realization term with status accepted.",
                        Collections.emptyList()
                ),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(MergeFilesException.class)
    public ResponseEntity<ProblemDetail> mergeFiles(MergeFilesException exception) {
        return new ResponseEntity<>(
                new ProblemDetail(
                        "Cannot read files to merge. Exception throw " + exception.getExceptionMessage(),
                        Collections.emptyList()
                ),
                HttpStatus.CONFLICT
        );
    }
}
