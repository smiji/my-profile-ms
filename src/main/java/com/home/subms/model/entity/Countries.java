package com.home.subms.model.entity;

import com.home.subms.model.CountryRequestDTO;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "Countries")
public class Countries {

    @Id
    private String id;
    private String name;
    private String dialCode;
    private String isoCode;

    public Countries(String name,String dialCode,String isoCode){
        this.id=UUID.randomUUID().toString();
        this.name=name;
        this.dialCode=dialCode;
        this.isoCode=isoCode;
    }

    public Countries(){
    }

    public Countries(CountryRequestDTO countryRequestDTO){
        this.id = UUID.randomUUID().toString();
        this.name=countryRequestDTO.name();
        this.dialCode=countryRequestDTO.dialCode();
        this.isoCode=countryRequestDTO.isoCode();
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

    public String getDialCode() {
        return dialCode;
    }

    public void setDialCode(String dialCode) {
        this.dialCode = dialCode;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    @Override
    public String toString() {
        return "Countries{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", dialCode='" + dialCode + '\'' +
                ", isoCode='" + isoCode + '\'' +
                '}';
    }
}
