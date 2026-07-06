package com.home.subms.controller;

import com.home.subms.ProfileMainStarter;
import com.home.subms.model.CityRequestDTO;
import com.home.subms.model.CountryRequestDTO;
import com.home.subms.model.CountryResponseDTO;
import com.home.subms.service.CitiesServiceImpl;
import com.home.subms.service.CountriesServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(classes = ProfileMainStarter.class)
@AutoConfigureMockMvc
class LookUpControllerIT {


    @Autowired
    MockMvc mockMvc;
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    CitiesServiceImpl citiesService;

    @Autowired
    CountriesServiceImpl countriesService;

    @BeforeEach
    void setUp() {
        mongoTemplate.dropCollection("Cities");
        mongoTemplate.dropCollection("Countries");
        CountryResponseDTO countryResponseDTOAR = countriesService.add(createRequest("Argentina", "AR", "54"));
        CountryResponseDTO countryResponseDTOAM = countriesService.add(createRequest("Armenia", "AM", "374"));
        CountryResponseDTO countryResponseDTOAW = countriesService.add(createRequest("Aruba", "AW", "297"));
        citiesService.add(createCityRequest("AAAA", countryResponseDTOAR.id()));
        citiesService.add(createCityRequest("AB", countryResponseDTOAM.id()));
        citiesService.add(createCityRequest("BA", countryResponseDTOAW.id()));
    }
    @AfterEach
    void rollBack(){
        mongoTemplate.dropCollection("Cities");
        mongoTemplate.dropCollection("Countries");
    }
    @Test
    void testLoadCountries() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/lookup/cities/A");
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk()
                ).andExpect(MockMvcResultMatchers
                        .jsonPath("$.length()").value(2)
                ).andExpect(MockMvcResultMatchers.jsonPath("$[0].cityName").value("AAAA")
                ).andExpect(MockMvcResultMatchers.jsonPath("$[1].cityName").value("AB")
                ).andExpect(MockMvcResultMatchers.jsonPath("$[0].isoCode").value("AR")
                ).andExpect(MockMvcResultMatchers.jsonPath("$[1].isoCode").value("AM"));
    }

    private CityRequestDTO createCityRequest(String name, String countryId) {
        return new CityRequestDTO(null, name, countryId);
    }

    private CountryRequestDTO createRequest(String name, String iso, String phoneCode) {
        return new CountryRequestDTO(null, name, phoneCode, iso);
    }
}