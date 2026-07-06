package com.home.subms.repo;

import com.home.subms.model.entity.CVProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends MongoRepository<CVProfile, String> {
}
