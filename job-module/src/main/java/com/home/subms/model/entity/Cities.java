package com.home.subms.model.entity;

import com.home.subms.model.CityRequestDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "Cities")
public class Cities {

    @Id
    private String id;
    private String name;
    private String countryId;

    public Cities() {
    }

    public Cities(String name, String countryId) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.countryId = countryId;
    }

    public Cities(CityRequestDTO cityRequestDTO) {
        this.id = UUID.randomUUID().toString();
        this.name = cityRequestDTO.name();
        this.countryId = cityRequestDTO.countryId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return "Cities{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", countryId='" + countryId + '\'' + '}';
    }
}
