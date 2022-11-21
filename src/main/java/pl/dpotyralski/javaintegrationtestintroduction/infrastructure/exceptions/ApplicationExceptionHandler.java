package pl.dpotyralski.javaintegrationtestintroduction.infrastructure.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static pl.dpotyralski.javaintegrationtestintroduction.infrastructure.exceptions.ErrorCodes.VALIDATION_ERROR;

@Slf4j
@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponse> handle(ApplicationException ex) {
        log.warn("Handling {} - code: {}, message: {}", ex.getClass().getSimpleName(), ex.getCode(), ex.getLocalizedMessage());
        return new ResponseEntity<>(new ErrorResponse(ex.getCode(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handle(MethodArgumentNotValidException ex) {
        log.warn("Handling {} - code: {}, message: {}", ex.getClass().getSimpleName(), VALIDATION_ERROR, ex.getLocalizedMessage());
        ValidationErrorResponse errorResponse = new ValidationErrorResponse(VALIDATION_ERROR, "Validation failed");
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errorResponse.getFieldErrors().add(new ValidationErrorResponse.FieldValidationError(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
