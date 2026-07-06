package com.home.subms.service;

import com.home.subms.exception.ItemNotFoundException;
import com.home.subms.model.CityRequestDTO;
import com.home.subms.model.CityResponseDTO;
import com.home.subms.model.ResponseMapper;
import com.home.subms.model.entity.Cities;
import com.home.subms.repo.CityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CitiesServiceImpl implements ICitiesService {

    private final CityRepository cityRepository;


    public CitiesServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public CityResponseDTO add(CityRequestDTO cityRequestDTO) {
        return ResponseMapper.mapToResponse(cityRepository.save(new Cities(cityRequestDTO)));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean addAll(List<Cities> cities) {
        cityRepository.saveAll(cities);
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CityResponseDTO update(CityRequestDTO cityRequestDTO, String id) {
        Cities savedCity = cityRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(String.format("No item exist for id [%s]", id)));
        savedCity.setName(cityRequestDTO.name());
        savedCity.setCountryId(cityRequestDTO.countryId());
        return ResponseMapper.mapToResponse(cityRepository.save(savedCity));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CityResponseDTO partialUpdate(CityRequestDTO cityRequestDTO, String id) {
        Cities savedCity = cityRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(String.format("No item exist for id [%s]", id)));
        if (savedCity.getCountryId() != null || !savedCity.getCountryId().isEmpty()) {
            savedCity.setCountryId(savedCity.getCountryId());
        }
        if (savedCity.getName() != null || !savedCity.getName().isEmpty()) {
            savedCity.setName(savedCity.getName());
        }
        return ResponseMapper.mapToResponse(savedCity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(String id) {
        Optional<Cities> savedCity = cityRepository.findById(id);
        if (savedCity.isPresent()) {
            cityRepository.deleteById(id);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public CityResponseDTO getCityById(String id) {
        return ResponseMapper.mapToResponse(cityRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(String.format("No item exist for id [%s]", id))));
    }

    @Override
    public boolean hadDataLoaded() {
        long rowCount = cityRepository.count();
        System.out.println("Number of records::" + rowCount);
        return rowCount > 0;
    }
}
