package com.okta.springbootvue;

import com.okta.springbootvue.Entity.*;
import com.okta.springbootvue.Repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
public class MovieGenreTests {

    private Validator validator;

    @Autowired
    private MovieGenreRepository moviegenreRepository ;


    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

     @Test
    void B6014223_testGenreOk() {
        MovieGenre moviegenre = new MovieGenre();
        moviegenre.setMovieGenre("horror");
        
        

        moviegenre = moviegenreRepository.saveAndFlush(moviegenre);
        
        Optional<MovieGenre> found = moviegenreRepository.findById(moviegenre.getMovieGenre_id());
        assertEquals("horror", found.get().getMovieGenre());
        
    }
    @Test
    void B6014223_testGenreMustNotBeNull() {
        MovieGenre moviegenre = new MovieGenre();
        moviegenre.setMovieGenre(null);

        

        Set<ConstraintViolation<MovieGenre>> result = validator.validate(moviegenre);

        // result ต้องมี error 1 ค่าเท่านั้น
         assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<MovieGenre> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("genre", v.getPropertyPath().toString());
    }

}