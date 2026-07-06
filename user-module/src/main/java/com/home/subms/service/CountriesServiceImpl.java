package com.home.subms.service;

import com.home.subms.exception.ItemNotFoundException;
import com.home.subms.model.CountryRequestDTO;
import com.home.subms.model.CountryResponseDTO;
import com.home.subms.model.ResponseMapper;
import com.home.subms.model.entity.Countries;
import com.home.subms.repo.CountryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountriesServiceImpl implements ICountriesService {

    private final CountryRepository countryRepository;

    public CountriesServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CountryResponseDTO add(CountryRequestDTO countryRequestDTO) {
        return ResponseMapper.mapToResponse(countryRepository.save(new Countries(countryRequestDTO)));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean addAll(List<Countries> lsCountries) {
        countryRepository.saveAll(lsCountries);
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CountryResponseDTO update(CountryRequestDTO countryRequestDTO, String id) {
        Countries savedCountry = countryRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(String.format("No item exist for [%s]", id)));
        savedCountry.setId(id);
        savedCountry.setName(countryRequestDTO.name());
        savedCountry.setDialCode(countryRequestDTO.dialCode());
        savedCountry.setIsoCode(countryRequestDTO.isoCode());
        return ResponseMapper.mapToResponse(countryRepository.save(savedCountry));
    }

    @Override
    public CountryResponseDTO partialUpdate(CountryRequestDTO countryRequestDTO, String id) {
        Countries savedCountry = countryRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(String.format("No item exist for [%s]", id)));
        if (countryRequestDTO.isoCode() != null && !countryRequestDTO.isoCode().isEmpty()) {
            savedCountry.setIsoCode(countryRequestDTO.isoCode());
        }
        if (countryRequestDTO.dialCode() != null && !countryRequestDTO.dialCode().isEmpty()) {
            savedCountry.setIsoCode(countryRequestDTO.isoCode());
        }
        if (countryRequestDTO.name() != null && !countryRequestDTO.name().isEmpty()) {
            savedCountry.setName(countryRequestDTO.name());
        }
        return ResponseMapper.mapToResponse(countryRepository.save(savedCountry));
    }

    @Override
    public void delete(String id) {
        if (countryRepository.findById(id).isPresent()) {
            countryRepository.deleteById(id);
        }
    }

    @Override
    public CountryResponseDTO getCountry(String id) {
        return ResponseMapper.mapToResponse(countryRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(String.format("No item exist for [%s]", id))));
    }

    @Override
    public List<CountryResponseDTO> getAllCountries() {
        return countryRepository.findAll().stream().map(ResponseMapper::mapToResponse).toList();
    }

    @Override
    public void clearAll() {
        countryRepository.deleteAll();
    }

    @Override
    public boolean hasLoaded() {
        long rowCount = countryRepository.count();
        System.out.println("Number of countries exists:" + rowCount);
        return rowCount > 0;
    }
}
