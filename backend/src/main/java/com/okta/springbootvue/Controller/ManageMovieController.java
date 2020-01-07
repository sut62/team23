package com.okta.springbootvue.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;


import java.util.stream.Collectors;

import com.okta.springbootvue.Entity.*;
import com.okta.springbootvue.Repository.*;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ManageMovieController {
    @Autowired
    private final ManageMovieRepository manageMovieRepository;
    @Autowired
    private MovieAddRepository movieAddRepository;
    @Autowired
    private DubRepository dubRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private MovieTimeRepository movieTimeRepository;

    ManageMovieController(ManageMovieRepository manageMovieRepository, MovieAddRepository movieAddRepository,
            DubRepository dubRepository, RoomRepository roomRepository, MovieTimeRepository movieTimeRepository) {

        this.manageMovieRepository = manageMovieRepository;
        this.movieAddRepository = movieAddRepository;
        this.dubRepository = dubRepository;
        this.roomRepository = roomRepository;
        this.movieTimeRepository = movieTimeRepository;

    }

    @GetMapping("/managemovie")
    public List<ManageMovie> ManageMovies() {
        return manageMovieRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/managemovie/{movie_id}/{dub_id}/{room_id}/{movie_time}/{movie_day}")
    public ManageMovie newManageMovie(ManageMovie newManageMovie, @PathVariable long movie_id,
            @PathVariable long dub_id, @PathVariable long room_id, @PathVariable String movie_day, @PathVariable long movie_time) {

        MovieAdd movieAdd = movieAddRepository.findById(movie_id);
        Dub lan = dubRepository.findById(dub_id);
        Room ro = roomRepository.findById(room_id);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
        Date date = formatter.parse(movie_day);
        newManageMovie.setDate(date);
            } catch (ParseException e) {
        }
        
        MovieTime time = movieTimeRepository.findById(movie_time);

        newManageMovie.setMovie(movieAdd);
        newManageMovie.setLan(lan);
        newManageMovie.setRo(ro);
        newManageMovie.setTime(time);

        return manageMovieRepository.save(newManageMovie);

    }
}