package com.home.subms.model;

import com.home.subms.model.entity.Cities;
import com.mongodb.lang.NonNull;

public record CityResponseDTO(String id, String name, String countryId) {
    public CityResponseDTO(Cities cities) {
        this(cities == null ? null : cities.getId(), cities == null ? null : cities.getName(), cities == null ? null : cities.getCountryId());
    }

    @Override
    @NonNull
    public String toString() {
        return "CityResponseDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", countryId='" + countryId + '\'' +
                '}';
    }
}
