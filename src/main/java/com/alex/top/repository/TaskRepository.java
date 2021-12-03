package com.alex.top.repository;

import com.alex.top.model.CelebsTask;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TaskRepository extends MongoRepository<CelebsTask, Integer> {

    Optional<CelebsTask> findByResetTop(String resetTop);
}
