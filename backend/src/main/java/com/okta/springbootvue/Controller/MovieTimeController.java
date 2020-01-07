package com.okta.springbootvue.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import com.okta.springbootvue.Entity.*;
import com.okta.springbootvue.Repository.*;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class MovieTimeController {

    @Autowired
    private final MovieTimeRepository movieTimeRepository;

    public MovieTimeController(MovieTimeRepository movieTimeRepository) {
        this.movieTimeRepository = movieTimeRepository;
    }

    @GetMapping("/time")
    public List<MovieTime> movieTimes() {
        return movieTimeRepository.findAll().stream().collect(Collectors.toList());
    }

}