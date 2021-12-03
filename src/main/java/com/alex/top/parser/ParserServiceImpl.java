package com.alex.top.parser;

import com.alex.top.model.Celebrity;
import com.alex.top.model.ParserTask;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

@Service
public class ParserServiceImpl implements ParserService {

    @Override
    public List<Future<Celebrity>> parse(String link) throws InterruptedException {

        // TODO change count to 100
        CountDownLatch countDownLatch = new CountDownLatch(0);
        // TODO move and change to property size
        ExecutorService executor = Executors.newFixedThreadPool(10);

        List<ParserTask> parserTasks = parseTopCelebrities(link, countDownLatch);

        List<Future<Celebrity>> responses = new ArrayList<>();
        for (ParserTask task : parserTasks) {
            responses.add(executor.submit(task));
        }

        // TODO change time of waiting for all results
        countDownLatch.await(60, TimeUnit.SECONDS);

        return responses;
    }

    // TODO need to implement using countDownLatch
    private List<ParserTask> parseTopCelebrities(String link, CountDownLatch countDownLatch) {
        // TODO all information about celebrity on main page by link
        // TODO for information about work we have to go to additional page
        // Elements repositories = doc.getElementsByClass("lister-list");
        return Collections.emptyList();
    }
}
