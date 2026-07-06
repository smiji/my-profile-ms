package com.home.subms.service;

import com.home.subms.model.CityRequestDTO;
import com.home.subms.model.CityResponseDTO;
import com.home.subms.model.entity.Cities;

import java.util.List;

public interface ICitiesService {
    CityResponseDTO add(CityRequestDTO cityRequestDTO);

    boolean addAll(List<Cities> lstCities);

    CityResponseDTO update(CityRequestDTO cityRequestDTO, String id);

    CityResponseDTO partialUpdate(CityRequestDTO cityRequestDTO, String id);

    CityResponseDTO getCityById(String id);

    void delete(String id);

    boolean hadDataLoaded();

}
