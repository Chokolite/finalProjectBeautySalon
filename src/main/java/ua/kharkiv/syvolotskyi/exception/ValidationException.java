package ua.kharkiv.syvolotskyi.exception;

import java.util.Map;

public class ValidationException extends RuntimeException {
    private Map<String, ValidationEnum> errors;

    ValidationException(Map<String, ValidationEnum> errors) {
        this.errors = errors;
    }

    public static ValidationExceptionBuilder builder(){
        return new ValidationExceptionBuilder();
    }

    public Map<String, ValidationEnum> getErrors() {
        return errors;
    }
}
