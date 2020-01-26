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
public class SystemTypeTests {

    private Validator validator;

    @Autowired
    private SystemTypeRepository systemtypeRepository ;


    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

     @Test
    void B6014223_testSystemTypeOk() {
        SystemType systemtype = new SystemType();
        systemtype.setSystemType("4DX");
        
        

        systemtype = systemtypeRepository.saveAndFlush(systemtype);
        
        Optional<SystemType> found = systemtypeRepository.findById(systemtype.getSystemType_id());
        assertEquals("4DX", found.get().getSystemType());
        
    }
    @Test
    void B6014223_testSystemTypeMustNotBeNull() {
        SystemType systemtype = new SystemType();
        systemtype.setSystemType(null);

        

        Set<ConstraintViolation<SystemType>> result = validator.validate(systemtype);

        // result ต้องมี error 1 ค่าเท่านั้น
         assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<SystemType> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("systemtype", v.getPropertyPath().toString());
    }

}