package com.home.subms.validations;

import com.home.subms.model.CVProfileRequestDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;

class ProfanityValidatorTest {

    private Validator validator;

    @BeforeEach
    public void beforeEach() {
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            this.validator = validatorFactory.getValidator();
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"This is a clean code no abusive words."})
    void testProfinityValidator(String message) {
        System.out.println(message);
        CVProfileRequestDTO request = new CVProfileRequestDTO();
        Set<ConstraintViolation<CVProfileRequestDTO>> violations = validator.validate(request);
        Assertions.assertEquals(0, violations.size());
    }

    @ParameterizedTest
    @ValueSource(strings = {"This is fuck abusive statement"})
    void testAbusiveWords(String message) {
        CVProfileRequestDTO request = new CVProfileRequestDTO();
        request.setData(message);
        Set<ConstraintViolation<CVProfileRequestDTO>> violations = validator.validate(request);
        Assertions.assertEquals(1, violations.size());
    }

}

class Request {
    private String message;

    public Request(String string) {
        this.message = string;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}