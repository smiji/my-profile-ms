package com.home.subms.controller;

import com.home.subms.model.CVProfileRequestDTO;
import com.home.subms.model.CVProfileResponseDTO;
import com.home.subms.service.IProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
@CrossOrigin(origins = "http://localhost:3000")
public class CVProfileController {

    private final IProfileService profileService;

    public CVProfileController(IProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public ResponseEntity<CVProfileResponseDTO> create(@RequestBody CVProfileRequestDTO CVProfileRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(profileService.createProfile(CVProfileRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CVProfileResponseDTO> update(@RequestBody CVProfileRequestDTO CVProfileRequestDTO, @PathVariable String id){
        return ResponseEntity.ok(profileService.updateProfile(CVProfileRequestDTO,id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        profileService.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }
}
