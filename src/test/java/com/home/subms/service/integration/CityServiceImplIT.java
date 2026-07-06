package com.home.subms.service.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.subms.ProfileMainStarter;
import com.home.subms.service.CitiesServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = ProfileMainStarter.class)
public class CityServiceImplIT {
    @Autowired
    CitiesServiceImpl citiesService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void test() {
        System.out.println("I am testing..");
    }

}
