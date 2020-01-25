package com.okta.springbootvue;

import com.okta.springbootvue.Entity.MovieTime;
import com.okta.springbootvue.Repository.MovieTimeRepository;
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
public class MovieTimeTest {

    private Validator validator;

    @Autowired
    private MovieTimeRepository movieTimeRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void B6011987_testMovieTimeNormal() {
        MovieTime time = new MovieTime();
        time.setTime("15:15");

        time = movieTimeRepository.saveAndFlush(time);

        Optional<MovieTime> found = movieTimeRepository.findById(time.getIdT());
        assertEquals("15:15", found.get().getTime());
    }
    void B6011987_testTimeMustNotBeNull() {
        MovieTime time = new MovieTime();
        time.setTime(null);

        Set<ConstraintViolation<MovieTime>> result = validator.validate(time);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<MovieTime> x = result.iterator().next();
        assertEquals("must not be null", x.getMessage());
        assertEquals("time", x.getPropertyPath().toString());
    }
	@Test
    void B6011987_testMovieTimeCenterCharacter(){
        MovieTime time = new MovieTime();
        time.setTime("15.15");
        
        Set<ConstraintViolation<MovieTime>> result = validator.validate(time);

        assertEquals(1, result.size());

        ConstraintViolation<MovieTime> message = result.iterator().next();
        assertEquals("must match \"\\d{2}[:]\\d{2}\"",message.getMessage());
        assertEquals("time",message.getPropertyPath().toString());
    }
    
}