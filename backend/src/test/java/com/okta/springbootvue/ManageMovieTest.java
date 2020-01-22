package com.okta.springbootvue;

import com.okta.springbootvue.Entity.*;
import com.okta.springbootvue.Repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

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
    @Test
    void B6011987_testManageMoiveNormal() {
        ManageMovie movie = new ManageMovie();
        movie.setNote("kidmaioog");
        movie = manageMovieRepository.saveAndFlush(movie);


        Optional<ManageMovie> found = manageMovieRepository.findById(movie.getId());
        assertEquals("kidmaioog", found.get().getNote());
    }
    @Test
    void B6011987_testNoteIsMoreThanSize() {
        ManageMovie movie = new ManageMovie();
        movie.setNote("123456789012345678901234567890123456789");

        Set<ConstraintViolation<ManageMovie>> result = validator.validate(movie);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<ManageMovie> v = result.iterator().next();
        assertEquals("note", v.getPropertyPath().toString());
    }

    
	
    
}