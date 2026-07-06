package com.home.subms.service;


import com.home.subms.model.CVProfileRequestDTO;
import com.home.subms.model.CVProfileResponseDTO;

public interface IProfileService {
    CVProfileResponseDTO createProfile(CVProfileRequestDTO CVProfileRequestDTO);
    CVProfileResponseDTO updateProfile(CVProfileRequestDTO CVProfileRequestDTO, String id);
    CVProfileResponseDTO getProfile(String id);
    void deleteProfile(String id);
}

