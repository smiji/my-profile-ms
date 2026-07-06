package com.home.subms.service;

import com.home.subms.model.LookUpCityCountry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LookUpCityCountryServiceImpl implements ILookUpCityCountryService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<LookUpCityCountry> getCityCountryDetails(String namePrefix) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("name").regex("^" + namePrefix, "i")),
                Aggregation.lookup("Countries", "countryId", "_id", "countryDetails"),
                Aggregation.unwind("countryDetails"),
                Aggregation.project()
                        .and("$_id").as("cityId")
                        .and("name").as("cityName")
                        .and("countryId").as("countryId")
                        .and("countryDetails.name").as("countryName")
                        .and("countryDetails.dialCode").as("dialCode")
                        .and("countryDetails.isoCode").as("isoCode"),
                Aggregation.sort(Sort.by(Sort.Direction.ASC,"cityName"))
        );
        AggregationResults<LookUpCityCountry> results = mongoTemplate.aggregate(aggregation, "Cities",
                LookUpCityCountry.class);
        return results.getMappedResults();
    }
}
