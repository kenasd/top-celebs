package com.alex.top.service;

import com.alex.top.model.Celebrity;
import com.alex.top.repository.CelebrityRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CelebrityServiceImpl implements CelebrityService {

    private final CelebrityRepository celebrityRepository;

    @Override
    public List<Celebrity> getAllCelebsByJob(String job) {
        if(Strings.isBlank(job)){
            return Collections.emptyList();
        }
        return celebrityRepository.findByJobsIn(job);
    }

    // TODO we have copyOnWrite behavior, we are reading old data and wait to replace all by one transaction
    @Override
    @Transactional
    public boolean replaceAllCelebs(List<Celebrity> celebrities) {
        if (celebrities != null && celebrities.size() > 0) {
            celebrityRepository.deleteAll();
            List<Celebrity> saved = celebrityRepository.saveAll(celebrities);
            return saved.size() > 0;
        }
        return false;
    }
}