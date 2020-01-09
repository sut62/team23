package com.okta.springbootvue.Controller;



import com.okta.springbootvue.Entity.MovieRate;
import com.okta.springbootvue.Repository.MovieRateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class MovieRateController {

    @Autowired
    private MovieRateRepository movierateRepository;

    public MovieRateController(MovieRateRepository movierateRepository) {
        this.movierateRepository = movierateRepository;
    }

    @GetMapping("/movierate")
    public Collection<MovieRate> MovieRates() {
        return movierateRepository.findAll().stream().collect(Collectors.toList());
    }
    
}