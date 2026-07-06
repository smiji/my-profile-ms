package com.home.subms.model;

import com.home.subms.model.entity.Countries;

public record CountryResponseDTO(String id, String name, String dialCode, String isoCode) {
    public CountryResponseDTO(Countries countries) {
        this(countries == null ? null : countries.getId(), countries == null ? null : countries.getName(), countries == null ? null : countries.getDialCode(), countries == null ? null : countries.getIsoCode());
        if (countries == null) {
            throw new IllegalArgumentException("CountryFromJson cannot be null");
        }
    }
}
