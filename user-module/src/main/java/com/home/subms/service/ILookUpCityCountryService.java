package com.home.subms.service;

import com.home.subms.model.LookUpCityCountry;

import java.util.List;

public interface ILookUpCityCountryService {
    List<LookUpCityCountry> getCityCountryDetails(String namePrefix);
}
