package com.home.subms.model;

import com.home.subms.validations.ProfanityFilter;
import jakarta.validation.Valid;

@Valid
public class CVProfileRequestDTO {

    @ProfanityFilter(message = "Words are not appropriate")
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

