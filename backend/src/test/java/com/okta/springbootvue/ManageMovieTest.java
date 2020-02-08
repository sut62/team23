package com.okta.springbootvue;

import com.okta.springbootvue.Entity.*;
import com.okta.springbootvue.Repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
public class ManageMovieTest {

    private Validator validator;

    @Autowired
    private ManageMovieRepository manageMovieRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    /*@Test
    void B6011987_TestDateisCorrect(){
        ManageMovie movie = new ManageMovie();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd") ;
        try {
            Date date = formatter.parse("2020-01-13");
            movie.setDate(date) ;
        } catch (ParseException e) {}
        
        movie = manageMovieRepository.saveAndFlush(movie);

        Optional<ManageMovie> found = manageMovieRepository.findById(movie.getId());
        try {
            assertEquals(formatter.parse("2020-01-13"), found.get().getDate());
        } catch (ParseException e) {}
    }*/
    @Test
    void B6011987_testManageMoiveNormal() {
        ManageMovie movie = new ManageMovie();
        movie.setNote("kidmaioog");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd") ;
        try {
            Date date = formatter.parse("2020-01-13");
            movie.setDate(date) ;
        } catch (ParseException e) {}
        movie = manageMovieRepository.saveAndFlush(movie);
        Optional<ManageMovie> found = manageMovieRepository.findById(movie.getId());
        assertEquals("kidmaioog", found.get().getNote());
        try {
            assertEquals(formatter.parse("2020-01-13"), found.get().getDate());
        } catch (ParseException e) {}
    }

    @Test
    void B6011987_testNoteIsMoreThanSize() {
        ManageMovie movie = new ManageMovie();
        movie.setNote("1234567890123456789012345678901234567890312156451");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd") ;
        try {
            Date date = formatter.parse("2020-01-13");
            movie.setDate(date) ;
        } catch (ParseException e) {}

        Set<ConstraintViolation<ManageMovie>> result = validator.validate(movie);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<ManageMovie> c = result.iterator().next();
        assertEquals("must be less than or equal 30 characters", c.getMessage());
        assertEquals("note", c.getPropertyPath().toString());
    }
    @Test
    void B6011987_testDateMustNotBeNull() {
        ManageMovie movie = new ManageMovie();
        movie.setNote("kidmaioog");
        try {
            movie.setDate(null);
            //movie = manageMovieRepository.saveAndFlush(movie);
        } catch (ConstraintViolationException e) {}

        Set<ConstraintViolation<ManageMovie>> result = validator.validate(movie);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<ManageMovie> d = result.iterator().next();
        assertEquals("must not be null", d.getMessage());
        assertEquals("date", d.getPropertyPath().toString());
    }

    
	
    
}