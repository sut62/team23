package com.okta.springbootvue;

import com.okta.springbootvue.Entity.Country;
import com.okta.springbootvue.Repository.CountryRepository;
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
public class CountryTest {
    private Validator validator;

    @Autowired
    private CountryRepository countryRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
   
    @Test
    void B6024055_TestCountryOk() {
        Country country = new Country();
        country.setCountry("ไทย");

        country = countryRepository.saveAndFlush(country);

        Optional<Country> found = countryRepository.findById(country.getCountry_id());
        assertEquals("ไทย", found.get().getCountry());
    }
}
