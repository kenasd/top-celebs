package com.alex.top.service;

import com.alex.top.model.Celebrity;

import java.util.List;

public interface CelebrityService {

    // TODO we can use Page instead of List for big count of data
    List<Celebrity> getAllCelebsByJob(String job);

    boolean replaceAllCelebs(List<Celebrity> celebrities);
}
