package com.home.subms.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.subms.exception.ConfigMissingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Component
public class ExtractMinRequiredInformation {

    private static final Function<CountryFromJson, MinInfoCountries> extractAndPrepareMinInfo = country -> new MinInfoCountries(country.getId(), country.getName(), country.getIso3(), country.getIso2(), country.getPhonecode());
    private static final Function<CitiesFromJson, MinInfoCities> transformToCity = cityFromJson -> new MinInfoCities(cityFromJson.name(), cityFromJson.subcountry(), cityFromJson.country());

    @Autowired
    ObjectMapper objectMapper;
    @Value("${app.json-path-countries}")
    String countryJsonPath;
    @Value("${app.json-path-cities}")
    String citiesJson;

    public List<MinInfoCountries> prepareCountriesMinInfo() {
        List<CountryFromJson> lstCountries = parseJsonCountry(countryJsonPath).orElseThrow(() -> new ConfigMissingException("Countries are not configured, correctly"));
        return lstCountries.stream().map(extractAndPrepareMinInfo).toList();
    }


    public List<MinInfoCities> prepareCitiesMinInfo() {
        List<CitiesFromJson> citiesFromJson = parseJsonCity(citiesJson).orElseThrow(() -> new ConfigMissingException("Cities are not configured, correctly"));
        return citiesFromJson.stream().map(transformToCity).toList();
    }

    Optional<List<CitiesFromJson>> parseJsonCity(String jsonPath) {
        try {
            return Optional.of(objectMapper.readValue(new File(jsonPath), objectMapper.getTypeFactory().constructCollectionType(List.class, CitiesFromJson.class)));
        } catch (IOException ioException) {
            throw new ConfigMissingException("CountryFromJson json is missing:" + ioException);
        }
    }

    Optional<List<CountryFromJson>> parseJsonCountry(String jsonPath) {
        try {
            return Optional.of(objectMapper.readValue(new File(jsonPath), objectMapper.getTypeFactory().constructCollectionType(List.class, CountryFromJson.class)));
        } catch (IOException ioException) {
            throw new ConfigMissingException("CountryFromJson json is missing:" + ioException);
        }
    }

}
