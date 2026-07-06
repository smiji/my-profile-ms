package com.home.subms.model;


import com.home.subms.model.entity.CVProfile;
import com.home.subms.model.entity.Cities;
import com.home.subms.model.entity.Countries;

public class ResponseMapper {

    public static CVProfileResponseDTO mapToResponse(CVProfile profile){
        return new CVProfileResponseDTO(profile);
    }

    public static CountryResponseDTO mapToResponse(Countries countries){
        return new CountryResponseDTO(countries);
    }

    public static CityResponseDTO mapToResponse(Cities cities){
        return new CityResponseDTO(cities);
    }

}
