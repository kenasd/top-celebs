package com.alex.top.service;

import com.alex.top.exception.ResetIsActiveException;
import com.alex.top.model.Celebrity;
import com.alex.top.model.CelebsTask;
import com.alex.top.model.Gender;
import com.alex.top.parser.ParserService;
import com.alex.top.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoaderServiceImpl implements LoaderService {

    private static final String RESET_TOP = "RT";
    private static final String LINK_TOP = "https://www.imdb.com/list/ls052283250/";

    private final CelebrityService celebrityService;
    private final ParserService parserService;

    private final TaskRepository taskRepository;

    // TODO we can use storing to DB loaded result to don`t lose its and continue after start
    @Override
    public boolean reset() throws ResetIsActiveException {
        CelebsTask celebsTask;
        synchronized (this) { // create distribution lock
            taskRepository.findByResetTop(RESET_TOP)
                    .ifPresent(task -> {
                        throw new ResetIsActiveException("Reset in process");
                    });
            celebsTask = createAndSaveTask();
        }

        List<Celebrity> celebrities = loadCelebrities();
        taskRepository.delete(celebsTask);
        return celebrityService.replaceAllCelebs(celebrities);
    }

    private List<Celebrity> loadCelebrities() {
        List<Celebrity> celebrities = new ArrayList<>();
        try {

            List<Future<Celebrity>> responses = parserService.parse(LINK_TOP);
            for (Future<Celebrity> future : responses) {
                celebrities.add(future.get());
            }

            // TODO temporary response
            Celebrity celebrity = new Celebrity();
            celebrity.setName("Johnny Depp");
            celebrity.setDob("1963-06-09");
            celebrity.setGender(Gender.MALE);
            celebrity.setJobs(Arrays.asList("Actor", "Producer", "Director"));
            celebrity.setImageURI("https://m.media-amazon.com/images/M/MV5BMTM0ODU5Nzk2OV5BMl5BanBnXkFtZTcwMzI2ODgyNQ@@._V1_UY317_CR4,0,214,317_AL_.jpg");
            celebrities.add(celebrity);

        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage());
        }
        return celebrities;
    }

    private CelebsTask createAndSaveTask() {
        CelebsTask celebsTask = new CelebsTask();
        celebsTask.setResetTop(RESET_TOP);
        return taskRepository.save(celebsTask);
    }

}
