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
public class MovieRateTests {

    private Validator validator;

    @Autowired
    private MovieRateRepository movierateRepository ;


    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

     @Test
    void B6014223_testRateOk() {
        MovieRate movierate = new MovieRate();
        movierate.setMovieRate("น18+");
        
        

        movierate = movierateRepository.saveAndFlush(movierate);
        
        Optional<MovieRate> found = movierateRepository.findById(movierate.getMovieRate_id());
        assertEquals("น18+", found.get().getMovieRate());
        
    }
    @Test
    void B6014223_testRateMustNotBeNull() {
        MovieRate movierate = new MovieRate();
        movierate.setMovieRate(null);

        

        Set<ConstraintViolation<MovieRate>> result = validator.validate(movierate);

        // result ต้องมี error 1 ค่าเท่านั้น
         assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<MovieRate> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("rate", v.getPropertyPath().toString());
    }

}