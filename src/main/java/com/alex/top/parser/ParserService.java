package com.alex.top.parser;

import com.alex.top.model.Celebrity;

import java.util.List;
import java.util.concurrent.Future;

public interface ParserService {

    List<Future<Celebrity>> parse(String link) throws InterruptedException;

}
