package com.home.subms.repo;

import com.home.subms.model.entity.Countries;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends MongoRepository<Countries,String> {
}
