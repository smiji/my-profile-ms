package com.home.subms.service;

import com.home.subms.model.CountryRequestDTO;
import com.home.subms.model.CountryResponseDTO;
import com.home.subms.model.entity.Countries;

import java.util.List;

public interface ICountriesService {
    CountryResponseDTO add(CountryRequestDTO countryRequestDTO);
    boolean addAll(List<Countries> countries);
    CountryResponseDTO update(CountryRequestDTO countryRequestDTO, String id);
    CountryResponseDTO partialUpdate(CountryRequestDTO countryRequestDTO, String id);
    void delete(String id);

    CountryResponseDTO getCountry(String id);
    List<CountryResponseDTO> getAllCountries();
    void clearAll();
    boolean hasLoaded();
}
