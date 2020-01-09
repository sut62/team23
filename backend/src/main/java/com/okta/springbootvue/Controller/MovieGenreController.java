package com.okta.springbootvue.Controller;



import com.okta.springbootvue.Entity.MovieGenre;
import com.okta.springbootvue.Repository.MovieGenreRepository;

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
public class MovieGenreController {

    @Autowired
    private MovieGenreRepository moviegenreRepository;

    public MovieGenreController(MovieGenreRepository moviegenreRepository) {
        this.moviegenreRepository = moviegenreRepository;
    }

    @GetMapping("/moviegenre")
    public Collection<MovieGenre> MovieGenres() {
        return moviegenreRepository.findAll().stream().collect(Collectors.toList());
    }
    
}