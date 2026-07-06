package com.home.subms.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class ProfanityValidator implements ConstraintValidator<ProfanityFilter, String> {

    private static final List<String> BLACKLIST = Arrays.asList("fuck", "suck");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) return true;
        String valueToLowerCase = value.toLowerCase();
        for (String word : BLACKLIST) {
            if (valueToLowerCase.contains(word)) {
                return false;
            }
        }
        return true;
    }
}
