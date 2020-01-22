package com.okta.springbootvue.Controller;

import com.okta.springbootvue.Entity.Employee;
import com.okta.springbootvue.Entity.MovieAdd;
import com.okta.springbootvue.Entity.MovieGenre;
import com.okta.springbootvue.Entity.MovieRate;
import com.okta.springbootvue.Entity.SystemType;
import com.okta.springbootvue.Repository.EmployeeRepository;
import com.okta.springbootvue.Repository.MovieAddRepository;
import com.okta.springbootvue.Repository.MovieGenreRepository;
import com.okta.springbootvue.Repository.MovieRateRepository;
import com.okta.springbootvue.Repository.SystemTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import java.text.ParseException;
import java.text.SimpleDateFormat;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class MovieAddController {

    @Autowired
    private MovieAddRepository movieaddRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private MovieGenreRepository moviegenreRepository;
    @Autowired
    private MovieRateRepository movierateRepository;
    @Autowired
    private SystemTypeRepository systemtypeRepository;

    public MovieAddController(MovieAddRepository movieaddRepository) {
        this.movieaddRepository = movieaddRepository;
    }

    @GetMapping("/movieadd/check/{name}")
    public MovieAdd checkMovieAdd(@PathVariable String name){
        return movieaddRepository.findByName(name);

    }

    @GetMapping("/movieadd")
    public Collection<MovieAdd> movieadd() {
        return movieaddRepository.findAll().stream().collect(Collectors.toList());
    }
    @PostMapping("/movieadd/{employee_id}/{name}/{genre_id}/{synopsis}/{director}/{actor}/{studio}/{rate_id}/{length}/{systemtype_id}")
    public MovieAdd newMovieAdd(MovieAdd newMovieAdd,
                        @PathVariable long employee_id,
                        @PathVariable String name,
                        @PathVariable long genre_id,
                        @PathVariable String synopsis,
                        @PathVariable String director,
                        @PathVariable String actor,
                        @PathVariable String studio,                       
                        @PathVariable long rate_id,
                        @PathVariable int length,
                        @PathVariable long systemtype_id
                        ) {

                                Employee employee = employeeRepository.findById(employee_id);
                                MovieGenre moviegenre = moviegenreRepository.findById(genre_id);
                                MovieRate movierate = movierateRepository.findById(rate_id);
                                SystemType systemtype = systemtypeRepository.findById(systemtype_id);
                                
                                MovieAdd movieadd = new MovieAdd();

                                
                                
                                movieadd.setEmployee(employee);
                                movieadd.setName(name);
                                movieadd.setMovieGenre(moviegenre);
                                movieadd.setSynopsis(synopsis);
                                movieadd.setDirector(director);
                                movieadd.setActor(actor);
                                movieadd.setStudio(studio);
                                movieadd.setMovieRate(movierate);
                                movieadd.setLength(length);
                                movieadd.setSystemType(systemtype);
                                movieaddRepository.save(movieadd);
                        
                        
                        
                        
                                return movieaddRepository.save(movieadd);
    }
}