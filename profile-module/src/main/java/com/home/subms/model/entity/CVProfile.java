package com.home.subms.model.entity;

import com.home.subms.model.CVProfileRequestDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "CVProfile")
public class CVProfile {

    @Id
    private String id;
    private String data;

    public CVProfile(){
    }
    public CVProfile(CVProfileRequestDTO CVProfileRequestDTO){
        this.data= CVProfileRequestDTO.getData();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
