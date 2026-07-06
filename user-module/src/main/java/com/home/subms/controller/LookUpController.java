package com.home.subms.controller;

import com.home.subms.model.LookUpCityCountry;
import com.home.subms.service.ILookUpCityCountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/lookup")
public class LookUpController {

    @Autowired
    private ILookUpCityCountryService lookUpCityCountryService;


    @GetMapping("/cities/{cityPrefix}")
    public ResponseEntity<List<LookUpCityCountry>> getCityCountryDetails(@PathVariable String cityPrefix) {
        return ResponseEntity.ok(lookUpCityCountryService.getCityCountryDetails(cityPrefix));
    }
}

