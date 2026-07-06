package com.home.subms.controller;

import com.home.subms.service.ConfigLoaderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/load")
public class LoadOnStartUpController {

    @Autowired
    ConfigLoaderServiceImpl configLoaderService;

    @GetMapping("/all")
    public void loadCountries() {
        configLoaderService.loadCountries();
        configLoaderService.loadCities();
        ResponseEntity.noContent().build();
    }
}
