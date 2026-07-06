package com.home.subms.service.integration;

import com.home.subms.ProfileMainStarter;
import com.home.subms.model.CityRequestDTO;
import com.home.subms.model.CountryRequestDTO;
import com.home.subms.model.CountryResponseDTO;
import com.home.subms.model.LookUpCityCountry;
import com.home.subms.service.ICitiesService;
import com.home.subms.service.ICountriesService;
import com.home.subms.service.ILookUpCityCountryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

@SpringBootTest(classes = ProfileMainStarter.class)
public class LookUpCityCountryFromJsonServiceIT {

    @Autowired
    ILookUpCityCountryService lookUpCityCountryService;

    @Autowired
    ICitiesService citiesService;

    @Autowired
    ICountriesService countriesService;

    @Autowired
    MongoTemplate mongoTemplate;

    @BeforeEach
    public void cleanUp() {
        mongoTemplate.dropCollection("Cities");
        mongoTemplate.dropCollection("Countries");
    }


    @Test
    void testLookUp() {
        CountryResponseDTO countryResponseDTOAR = countriesService.add(createRequest("Argentina", "AR", "54"));
        CountryResponseDTO countryResponseDTOAM = countriesService.add(createRequest("Armenia", "AM", "374"));
        CountryResponseDTO countryResponseDTOAW = countriesService.add(createRequest("Aruba", "AW", "297"));
        citiesService.add(createCityRequest("AAAA", countryResponseDTOAR.id()));
        citiesService.add(createCityRequest("AB", countryResponseDTOAM.id()));
        citiesService.add(createCityRequest("BA", countryResponseDTOAW.id()));
        List<LookUpCityCountry> lookUpResult = lookUpCityCountryService.getCityCountryDetails("A");
        lookUpResult.forEach(result -> {
            Assertions.assertTrue(result.getCityName().equalsIgnoreCase("AAAA") || result.getCityName().equalsIgnoreCase("AB"));
        });
    }

    @Test
    void testLookUpForUnmatch() {
        List<LookUpCityCountry> lookUpResult = lookUpCityCountryService.getCityCountryDetails("A");
        Assertions.assertEquals(0, lookUpResult.size());
    }

    private CityRequestDTO createCityRequest(String name, String countryId) {
        return new CityRequestDTO(null, name, countryId);
    }

    private CountryRequestDTO createRequest(String name, String iso, String phoneCode) {
        return new CountryRequestDTO(null, name, phoneCode, iso);
    }
}
