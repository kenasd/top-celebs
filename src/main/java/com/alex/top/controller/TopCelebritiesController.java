package com.alex.top.controller;

import com.alex.top.model.Celebrity;
import com.alex.top.service.CelebrityService;
import com.alex.top.service.LoaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TopCelebritiesController {

    private final CelebrityService celebrityService;
    private final LoaderService loaderService;

    @GetMapping("/celebrities")
    public ResponseEntity<List<Celebrity>> getAllCelebritiesByJob(@RequestParam(value = "job", required = false) String job) {
        return ResponseEntity.ok()
                .body(celebrityService.getAllCelebsByJob(job));
    }

    @PostMapping("/celebrities/reset")
    public ResponseEntity<Boolean> resetAllCelebrities() {
        return ResponseEntity.ok().body(this.loaderService.reset());
    }
}