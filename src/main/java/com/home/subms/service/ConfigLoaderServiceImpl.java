package com.home.subms.service;

import com.home.subms.data.ExtractMinRequiredInformation;
import com.home.subms.data.MinInfoCountries;
import com.home.subms.exception.ConfigMissingException;
import com.home.subms.model.entity.Cities;
import com.home.subms.model.entity.Countries;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Function;


@Service
public class ConfigLoaderServiceImpl implements IConfigLoaderService {

    private static final Function<MinInfoCountries, Countries> minInfoCountryFunction = minInfo -> new Countries(minInfo.name(), minInfo.iso2(), minInfo.phonecode());
    private static final String ERROR_MSG = "The country is not configured ";
    ICitiesService citiesService;
    ICountriesService countriesService;
    ExtractMinRequiredInformation extractMinRequiredInformation;
    ConcurrentHashMap<String, String> countryMap;


    public ConfigLoaderServiceImpl(ICitiesService citiesService, ICountriesService countriesService, ExtractMinRequiredInformation extractMinRequiredInformation, ConcurrentHashMap<String, String> countryMap) {
        this.citiesService = citiesService;
        this.countriesService = countriesService;
        this.extractMinRequiredInformation = extractMinRequiredInformation;
        this.countryMap = countryMap;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean loadCountries() {
        //check the tables are not already loaded
        if (!countriesService.hasLoaded()) {
            //countriesService.clearAll();
            List<Countries> countries = this.extractMinRequiredInformation.prepareCountriesMinInfo().stream().map(minInfoCountryFunction).toList();
            countriesService.addAll(countries);
            System.out.println("Number of countries added:"+countries.size());
            cacheLoadAllCountries();
        }

        return true;
    }

    @Override
    public boolean loadCities() {
        // check the tables are not already loaded..
        if (!citiesService.hadDataLoaded()) {
            List<Future<Cities>> futuresCitiesWithCountries = prepareFutureListForCities();
            List<Cities> processedCitiesToBeAdded = new ArrayList<>();
            for (Future<Cities> futureCities : futuresCitiesWithCountries) {
                try {
                    processedCitiesToBeAdded.add(futureCities.get());
                } catch (Exception e) {
                    //ignoring the errors
                    // System.out.println("Error::" + e);
                }
            }
            System.out.println("Number pf cities to be added.." + processedCitiesToBeAdded.size());
            return citiesService.addAll(processedCitiesToBeAdded);
        }
        // no need to return false if data already loaded
        return true;
    }

    public void cacheLoadAllCountries() {
        countriesService.getAllCountries().forEach(item -> {
            countryMap.put(item.name(), item.id());
        });
        System.out.println("Loaded the cache with items:"+countryMap.size());
    }


    private List<Future<Cities>> prepareFutureListForCities() {
        try (ExecutorService executor = Executors.newFixedThreadPool(5)) {
            return extractMinRequiredInformation.prepareCitiesMinInfo().stream().map(city -> executor.submit(() -> {
                String countryId = countryMap.get(city.country());
                if (countryId == null) {
                    throw new ConfigMissingException(city.country());
                }
                return new Cities(city.cityName(), countryId);
            })).toList();
        }

    }
}
