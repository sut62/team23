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
public class MovieAddController {

    @Autowired
    private final MovieAddRepository movieAddRepository;

    public MovieAddController(MovieAddRepository movieAddRepository) {
        this.movieAddRepository = movieAddRepository;
    }

    @GetMapping("/movie")
    public List<MovieAdd> MovieAdds() {
        return movieAddRepository.findAll().stream().collect(Collectors.toList());
    }

}