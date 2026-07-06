package com.home.subms.controller;

import com.home.subms.model.CVProfileRequestDTO;
import com.home.subms.model.CVProfileResponseDTO;
import com.home.subms.model.entity.CVProfile;
import com.home.subms.service.ProfileServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CVProfileController.class)
class CVProfileControllerTest {

    private static final String URI = "/profile";
    private static final String PROFILE_DATA = "{ I want to be a developer }";
    private static final String PROFILE_DATA_TO_UPDATE = "{ I want to be a Senior Developer }";
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProfileServiceImpl profileService;
    @Test
    void create() throws Exception {
        //Mock - Begin
        Mockito.when(profileService.createProfile(Mockito.any(CVProfileRequestDTO.class))).thenReturn(getResponseData());
        //Mock - End
        mockMvc.perform(
                post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getRequestData()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data")
                        .value(PROFILE_DATA));
    }

    @Test
    void update() throws Exception {
        mockMvc.perform(
                put(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(PROFILE_DATA_TO_UPDATE)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data")
                        .value("I want to be a Senior Developer"));
    }

    private String getRequestData() {
        return "{\"data\": \"" + PROFILE_DATA + "\"}";
    }

    private CVProfileResponseDTO getResponseData() {
        CVProfile cvProfile = new CVProfile();
        cvProfile.setData(PROFILE_DATA);
        cvProfile.setId("1L");
        return new CVProfileResponseDTO(cvProfile);
    }

}