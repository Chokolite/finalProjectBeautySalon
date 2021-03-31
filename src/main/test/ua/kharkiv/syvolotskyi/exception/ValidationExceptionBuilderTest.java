package ua.kharkiv.syvolotskyi.exception;

import org.junit.Test;

public class ValidationExceptionBuilderTest {
    @Test(expected = ValidationException.class)
    public void throwIfErrorExists() {
        ValidationExceptionBuilder validationExceptionBuilder = new ValidationExceptionBuilder();
        validationExceptionBuilder.put("BAD_CREDENTIAL", ValidationEnum.BAD_CREDENTIAL);
        validationExceptionBuilder.throwIfErrorExists();
    }
}
