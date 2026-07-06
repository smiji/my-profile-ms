package com.home.subms.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CountryJsonParser {
    public static List<CountryFromJson> parseCountries(String jsonFilePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(jsonFilePath), new TypeReference<List<CountryFromJson>>() {
        });
    }

    public static void main(String[] args) {
        try {
            String path = "/Users/smiji.j/learning/my-profile-ms/src/test/resources/data/countries.json";
            List<CountryFromJson> countries = parseCountries(path);
            System.out.println("Parsed " + countries.size() + " countries.");
            // Print first country as example
            if (!countries.isEmpty()) {
                System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(countries.get(0)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
