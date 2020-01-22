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
public class MovieAddTests {

    private Validator validator;

    @Autowired
    private MovieAddRepository movieaddRepository ;
    // @Autowired
    // private EmployeeRepository employeeRepository ;
    // @Autowired
    // private MovieGenreRepository moviegenreRepository ;
    // @Autowired
    // private MovieRateRepository movierateRepository ;
    // @Autowired
    // private SystemTypeRepository systemtypeRepository ;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
 /*   @Test
    void testPersonIdOKWith13Digits() {
        Person person = new Person();
        person.setPersonId("1234567890123

        person = personepository.saveAndFlush(person);

        Optional<Person> found = personRepository.findById(person.getId());
        assertEquals("1234567890123", found.get().getPersonId());
    }*/

     @Test
    void B6014223_testMovieAddOk() {
        MovieAdd movieadd = new MovieAdd();
        movieadd.setName("ROSE");
        movieadd.setSynopsis("sdggrepddfgtdd");
        movieadd.setDirector("rreoh");
        movieadd.setActor("deedass");
        movieadd.setStudio("dwdews");
        movieadd.setLength(155);
        // Employee employee = new Employee();
        // employee.setEmployee("สุบรรชา สวัสดี");
        // MovieGenre moviegenre = new MovieGenre();
        // moviegenre.setMovieGenre("romance");
        // MovieRate movierate = new MovieRate();
        // movierate.setMovieRate("น18+");
        // SystemType systemtype = new SystemType();
        // systemtype.setSystemType("4DX");

        movieadd = movieaddRepository.saveAndFlush(movieadd);
        // employee = employeeRepository.saveAndFlush(employee);
        // moviegenre = moviegenreRepository.saveAndFlush(moviegenre);
        // movierate = movierateRepository.saveAndFlush(movierate);
        // systemtype = systemtypeRepository.saveAndFlush(systemtype);

        Optional<MovieAdd> found = movieaddRepository.findById(movieadd.getId());
        assertEquals("ROSE", found.get().getName());
        assertEquals("sdggrepddfgtdd", found.get().getSynopsis());
        assertEquals("rreoh", found.get().getDirector());
        assertEquals("deedass", found.get().getActor());
        assertEquals("dwdews", found.get().getStudio());
        assertEquals(155, found.get().getLength());
        // Optional<Employee> a = employeeRepository.findById(employee.getId());
        // assertEquals("สุบรรชา สวัสดี", a.get().getEmployee());
        // Optional<MovieGenre> b = moviegenreRepository.findById(moviegenre.getId());
        // assertEquals("romance", b.get().getMovieGenre());
        // Optional<MovieRate> c = movierateRepository.findById(movierate.getId());
        // assertEquals("น18+", c.get().getMovieRate());
        // Optional<SystemType> d = systemtypeRepository.findById(systemtype.getId());
        // assertEquals("4DX", d.get().getSystemType());
    }
    @Test
    void B6014223_testNameMustNotBeNull() {
        MovieAdd movieadd = new MovieAdd();
        movieadd.setName(null);
        movieadd.setSynopsis("sdggrepddfgtdd");
        movieadd.setDirector("rreoh");
        movieadd.setActor("deedass");
        movieadd.setStudio("dwdews");
        movieadd.setLength(155);

        

        Set<ConstraintViolation<MovieAdd>> result = validator.validate(movieadd);

        // result ต้องมี error 1 ค่าเท่านั้น
         assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<MovieAdd> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }

   @Test
    void B6014223_testSynopsisMustMoreThan10Characters() {
        MovieAdd movieadd = new MovieAdd();
        movieadd.setName("ROSE");
        movieadd.setSynopsis("sdggr");
        movieadd.setDirector("rreoh");
        movieadd.setActor("deedass");
        movieadd.setStudio("dwdews");
        movieadd.setLength(155);


        Set<ConstraintViolation<MovieAdd>> result = validator.validate(movieadd);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<MovieAdd> v = result.iterator().next();
        assertEquals("Synopsis must be between 10 and 500 characters", v.getMessage());
        assertEquals("synopsis", v.getPropertyPath().toString());
    }

    @Test
    void B6014223_testLengthMustNotBeLessThan60() {
        MovieAdd movieadd = new MovieAdd();
        movieadd.setName("ROSE");
        movieadd.setSynopsis("sdggrepddfgtdd");
        movieadd.setDirector("rreoh");
        movieadd.setActor("deedass");
        movieadd.setStudio("dwdews");
        movieadd.setLength(55);

        Set<ConstraintViolation<MovieAdd>> result = validator.validate(movieadd);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<MovieAdd> v = result.iterator().next();
        assertEquals("length should not be less than 60", v.getMessage());
        assertEquals("length", v.getPropertyPath().toString());
    }


    @Test
    void B6014223_testLengthMustNotBeGreaterThan500() {
        MovieAdd movieadd = new MovieAdd();
        movieadd.setName("ROSE");
        movieadd.setSynopsis("sdggrepddfgtdd");
        movieadd.setDirector("rreoh");
        movieadd.setActor("deedass");
        movieadd.setStudio("dwdews");
        movieadd.setLength(550);

        Set<ConstraintViolation<MovieAdd>> result = validator.validate(movieadd);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<MovieAdd> v = result.iterator().next();
        assertEquals("length should not be greater than 500", v.getMessage());
        assertEquals("length", v.getPropertyPath().toString());
    }
    /*@Test
    void testPersonIdMustBeUnique() {
        // สร้าง person object
        Person p1 = new Person();
        p1.setPersonId("1234567890123");
        personRepository.saveAndFlush(p1);

        // คาดหวังว่า DataIntegrityViolationException จะถูก throw
        assertThrows(DataIntegrityViolationException.class, () -> {
            // สร้าง person object ตัวที่ 2
            Person p2 = new Person();
            p2.setPersonId("1234567890123");
            personRepository.saveAndFlush(p2);
        });
    }*/

}