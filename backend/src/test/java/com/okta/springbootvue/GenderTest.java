package com.okta.springbootvue;

import com.okta.springbootvue.Entity.Gender;
import com.okta.springbootvue.Repository.GenderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class GenderTest {

    private Validator validator;

    @Autowired
    private GenderRepository genderRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B6024055_TestGenderOk() {
        Gender gender = new Gender();
        gender.setGender_name("ชาย");

        gender = genderRepository.saveAndFlush(gender);

        Optional<Gender> found = genderRepository.findById(gender.getGender_id());
        assertEquals("ชาย", found.get().getGender_name());
    }

    @Test
    void B6024055_TestGenderMustNotNull(){
        Gender gender = new Gender();
        gender.setGender_name(null); // 12 digits

        Set<ConstraintViolation<Gender>> result = validator.validate(gender);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Gender> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("gender_name", v.getPropertyPath().toString());

    }

   


}
