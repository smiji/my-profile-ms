package com.home.subms.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

class ExtractMinRequiredInformationTest {

    private static final int COUNT_CITIES = 33623;
    private static final int COUNT_COUNTRY = 250;
    private static final String COUNTRY_JSON_PATH = "src/test/resources/data/countries.json";
    private static final String CITY_JSON_PATH = "src/test/resources/data/cities.json";

    ExtractMinRequiredInformation service;

    @BeforeEach
    public void setUp() {
        service = new ExtractMinRequiredInformation();
        ReflectionTestUtils.setField(service, "objectMapper", new ObjectMapper());
        ReflectionTestUtils.setField(service, "countryJsonPath", COUNTRY_JSON_PATH);
        ReflectionTestUtils.setField(service, "citiesJson", CITY_JSON_PATH);
    }


    @Test
    void testPrepareCitiesMinInfo() {
        List<MinInfoCities> minInfoCities = service.prepareCitiesMinInfo();
        Assertions.assertEquals(COUNT_CITIES, minInfoCities.size());
    }

    @Test
    void testPrepareCountriesMinInfo() {
        List<MinInfoCountries> minInfoCountries = service.prepareCountriesMinInfo();
        Assertions.assertEquals(COUNT_COUNTRY, minInfoCountries.size());
    }

    @Test
    void testParseJsonCity() {
        Optional<List<CitiesFromJson>> citiesFromJsons = service.parseJsonCity(CITY_JSON_PATH);
        citiesFromJsons.ifPresent(item -> Assertions.assertEquals(COUNT_CITIES, item.size()));
    }

    @Test
    void testParseJsonCountry() {
        Optional<List<CountryFromJson>> countriesFromJson = service.parseJsonCountry(COUNTRY_JSON_PATH);
        countriesFromJson.ifPresent(item -> Assertions.assertEquals(COUNT_COUNTRY, item.size()));
    }
}