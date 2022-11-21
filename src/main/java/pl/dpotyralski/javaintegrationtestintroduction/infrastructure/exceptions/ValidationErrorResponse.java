package pl.dpotyralski.javaintegrationtestintroduction.infrastructure.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
class ValidationErrorResponse {

    private final List<FieldValidationError> fieldErrors = new ArrayList<>();
    private int code;
    private String message;

    @Getter
    @AllArgsConstructor
    static class FieldValidationError {
        private String field;
        private String message;
    }

}
