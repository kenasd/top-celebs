package com.alex.top.repository;

import com.alex.top.model.Celebrity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CelebrityRepository extends MongoRepository<Celebrity, Integer> {

    List<Celebrity> findByJobsIn(String job);

}

