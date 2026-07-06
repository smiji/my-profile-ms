package com.home.subms.service.integration;

import com.home.subms.ProfileMainStarter;
import com.home.subms.model.CountryRequestDTO;
import com.home.subms.model.CountryResponseDTO;
import com.home.subms.model.entity.Countries;
import com.home.subms.service.ICountriesService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

@SpringBootTest(classes = ProfileMainStarter.class)
public class CountryFromJsonServiceImplIT {

    @Autowired
    ICountriesService countriesService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeEach
    public void beforeEach(){
        mongoTemplate.dropCollection("Countries");
    }

    @AfterEach
    public void afterAll(){
        mongoTemplate.dropCollection("Countries");
    }

    @Test
    void testAdd() {
        CountryResponseDTO countryResponseDTO = countriesService.add(createRequest());
        Assertions.assertEquals("Afghanistan", countryResponseDTO.name());
        Assertions.assertEquals("AF", countryResponseDTO.isoCode());
        Assertions.assertEquals("93", countryResponseDTO.dialCode());
    }

    @Test
    void testUpdate() {
        CountryResponseDTO countryResponseDTO = countriesService.add(createRequest());
        String anotherIsoCode = "AF_1";
        CountryRequestDTO updateRequest = updateRequest(countryResponseDTO.id());
        CountryResponseDTO countryResponseDTO2 = countriesService.update(updateRequest, countryResponseDTO.id());
        Assertions.assertEquals(anotherIsoCode, countryResponseDTO2.isoCode());
    }

    @Test
    void testPartialUpdate() {
        String name = "Afghanistan";
        CountryResponseDTO countryResponseDTO = countriesService.add(createRequest());
        CountryRequestDTO partCountryRequestDTO = partialUpdateRequest(countryResponseDTO.id());
        CountryResponseDTO updated = countriesService.partialUpdate(partCountryRequestDTO, countryResponseDTO.id());
        Assertions.assertEquals(name, updated.name());
    }

    @Test
    void testAddMany() {
        List<Countries> multipleRequest = createMultipleRequest();
        boolean batchCompleteStatus = countriesService.addAll(multipleRequest);
        Assertions.assertTrue(batchCompleteStatus);
    }

    @Test
    void testGet() {
        String name = "Afghanistan";
        CountryResponseDTO countryResponseDTO = countriesService.add(createRequest());
        CountryResponseDTO country = countriesService.getCountry(countryResponseDTO.id());
        Assertions.assertEquals(name, country.name());
    }

    @Test
    void testGetAll() {
        String name = "Afghanistan";
        boolean isCompleted = countriesService.addAll(createMultipleRequest_3());
        List<CountryResponseDTO> allCountries = countriesService.getAllCountries();
        allCountries.forEach(item->{
            System.out.println(item.name());
            Assertions.assertNotNull(item.id());
        });
        Assertions.assertEquals(allCountries.size(),3);
    }


    private CountryRequestDTO createRequest() {
        String id = null;
        String name = "Afghanistan";
        String iso = "AF";
        String phoneCode = "93";
        return new CountryRequestDTO(id, name, phoneCode, iso);
    }

    private CountryRequestDTO updateRequest(String id) {
        String name = "Afghanistan";
        String iso = "AF_1";
        String phoneCode = "93";
        return new CountryRequestDTO(id, name, phoneCode, iso);
    }

    private CountryRequestDTO partialUpdateRequest(String id) {
        String name = null;
        String iso = "AF_1";
        String phoneCode = null;
        return new CountryRequestDTO(id, name, phoneCode, iso);
    }

    private List<Countries> createMultipleRequest() {
        String id = null;
        String name = "Afghanistan";
        String iso = "AF";
        String phoneCode = "93";
        Countries countries1 = new Countries(name, phoneCode, iso);
        name = "Aland Islands";
        iso = "AX";
        phoneCode = "358";
        Countries countries2 = new Countries(name, phoneCode, iso);
        return List.of(countries1, countries2);
    }

    private List<Countries> createMultipleRequest_3() {
        String id = null;
        String name = "Afghanistan";
        String iso = "AF";
        String phoneCode = "93";
        Countries country1 = new Countries(name, phoneCode, iso);
        name = "Aland Islands";
        iso = "AX";
        phoneCode = "358";
        Countries country2 = new Countries(name, phoneCode, iso);

        name = "Albania";
        iso = "AL";
        phoneCode = "355";
        Countries country3 = new Countries(name, phoneCode, iso);
        return List.of(country1, country2, country3);
    }
}
