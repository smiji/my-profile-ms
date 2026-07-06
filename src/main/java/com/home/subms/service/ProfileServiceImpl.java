package com.home.subms.service;

import com.home.subms.exception.ItemNotFoundException;
import com.home.subms.model.ResponseMapper;
import com.home.subms.model.CVProfileRequestDTO;
import com.home.subms.model.CVProfileResponseDTO;
import com.home.subms.model.entity.CVProfile;
import com.home.subms.repo.ProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements IProfileService{

    ProfileRepository profileRepository;
    public ProfileServiceImpl(ProfileRepository profileRepository){
        this.profileRepository=profileRepository;
    }

    @Override
    public CVProfileResponseDTO createProfile(CVProfileRequestDTO CVProfileRequestDTO) {
        return ResponseMapper.mapToResponse(profileRepository.save(new CVProfile(CVProfileRequestDTO)));
    }

    @Override
    public CVProfileResponseDTO updateProfile(CVProfileRequestDTO CVProfileRequestDTO, String id) {
        CVProfile profileById = profileRepository
                .findById(id)
                .orElseThrow((()->new ItemNotFoundException(String.format("No item exist for [%s]",id))));
        profileById.setData(CVProfileRequestDTO.getData());
        return ResponseMapper.mapToResponse(profileRepository.save(profileById));
    }

    @Override
    public CVProfileResponseDTO getProfile(String id) {
        return null;
    }

    @Override
    public void deleteProfile(String id) {

    }
}
