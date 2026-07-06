package com.home.subms.repo;

import com.home.subms.model.entity.Cities;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends MongoRepository<Cities,String> {
}
