package com.home.subms.model;

import com.home.subms.model.entity.CVProfile;

public record CVProfileResponseDTO(String id, String data) {

    public CVProfileResponseDTO(CVProfile profile) {
        this(profile == null ? null : profile.getId(), profile == null ? null : profile.getData());
        if (profile == null) {
            throw new IllegalArgumentException("CVProfile cannot be null");
        }
    }
}
