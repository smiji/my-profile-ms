package com.home.subms.service;

import com.home.subms.model.CVProfileRequestDTO;
import com.home.subms.model.CVProfileResponseDTO;
import com.home.subms.model.entity.CVProfile;
import com.home.subms.repo.ProfileRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class ProfileServiceImplTest {

    @Mock
    private ProfileRepository profileRepository;
    @Mock
    private ProfileServiceImpl profileService;

    @InjectMocks
    private ProfileServiceImpl profileServiceImpl;

    @Test
    void createProfile() {
        //Mock handling - Begin
        Mockito.when(profileRepository.save(Mockito.any(CVProfile.class))).thenReturn(getProfileForInsert());
        //Mock handling - End
        CVProfileRequestDTO CVProfileRequestDTO = new CVProfileRequestDTO();
        CVProfileRequestDTO.setData(getDataJson());
        CVProfileResponseDTO savedProfile = profileServiceImpl.createProfile(CVProfileRequestDTO);
        assertNotNull(savedProfile);
        assertEquals("1L",savedProfile.id());
    }

    @Test
    void updateProfile() {
        CVProfile cvProfile = getProfileForUpdate();
        CVProfileRequestDTO CVProfileRequestDTO = new CVProfileRequestDTO();
        CVProfileRequestDTO.setData(cvProfile.getData());

        //Mock handling - Begin
        Mockito.when(profileRepository.findById(Mockito.any(String.class))).thenReturn(Optional.of(getProfileForUpdate()));
        Mockito.when(profileRepository.save(Mockito.any(CVProfile.class))).thenReturn(getProfileForUpdate());
        //Mock handling - End
        CVProfileResponseDTO profileResponseDTO = profileServiceImpl.updateProfile(CVProfileRequestDTO, "1L");
        Assertions.assertEquals(profileResponseDTO.data(),cvProfile.getData());
    }

    @Test
    void updateProfile_when_no_item_for_id() {
        String expectedErrorMessage = "No item exist for [1L]";
        CVProfile cvProfile = getProfileForUpdate();
        CVProfileRequestDTO CVProfileRequestDTO = new CVProfileRequestDTO();
        CVProfileRequestDTO.setData(cvProfile.getData());

        //Mock handling - Begin
        Mockito.when(profileRepository.findById(Mockito.any(String.class))).thenReturn(Optional.empty());
        //Mock handling - End
        CVProfileResponseDTO profileResponseDTO = profileServiceImpl.updateProfile(CVProfileRequestDTO, "1L");
        Assertions.assertEquals(profileResponseDTO.data(),cvProfile.getData());
    }

    void getProfile() {
    }

    void deleteProfile() {
    }

    private CVProfile getProfileForInsert() {
        CVProfile profile = new CVProfile();
        profile.setId("1L");
        profile.setData(getDataJson());
        return profile;
    }

    private CVProfile getProfileForUpdate() {
        CVProfile profile = new CVProfile();
        profile.setId("1L");
        profile.setData("I want to be a Senior Java Developer");
        return profile;
    }

    private String getDataJson() {
        return "{ I want to be a developer }";
    }

}