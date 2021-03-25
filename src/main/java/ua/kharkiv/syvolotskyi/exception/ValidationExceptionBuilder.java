package ua.kharkiv.syvolotskyi.exception;

import java.util.HashMap;
import java.util.Map;

public class ValidationExceptionBuilder {

    private Map<String, ValidationEnum> errors = new HashMap<>();

    public ValidationExceptionBuilder put(String key, ValidationEnum value) {
        errors.put(key, value);
        return this;
    }

    public void throwIfErrorExists() {
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

}
