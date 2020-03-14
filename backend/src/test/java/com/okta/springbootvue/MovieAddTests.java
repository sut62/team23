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
    @Autowired
    private EmployeeRepository employeeRepository ;
    @Autowired
    private MovieGenreRepository moviegenreRepository ;
    @Autowired
    private MovieRateRepository movierateRepository ;
    @Autowired
    private SystemTypeRepository systemtypeRepository ;


    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

     @Test
    void B6014223_testMovieAddOk() {
        MovieAdd movieadd = new MovieAdd();
        Employee employee = employeeRepository.findById(1);
        MovieGenre genre = moviegenreRepository.findById(1);
        MovieRate rate = movierateRepository.findById(2);
        SystemType systemtype = systemtypeRepository.findById(2);

        movieadd.setName("ROSE");
        movieadd.setSynopsis("sdggrepddfgtdd");
        movieadd.setDirector("rreoh");
        movieadd.setActor("deedass");
        movieadd.setStudio("dwdews");
        movieadd.setLength(155);
        movieadd.setEmployee(employee);
        movieadd.setMovieGenre(genre);
        movieadd.setMovieRate(rate);
        movieadd.setSystemType(systemtype);
        

        movieadd = movieaddRepository.saveAndFlush(movieadd);
        
        Optional<MovieAdd> found = movieaddRepository.findById(movieadd.getId());
        assertEquals("ROSE", found.get().getName());
        assertEquals("sdggrepddfgtdd", found.get().getSynopsis());
        assertEquals("rreoh", found.get().getDirector());
        assertEquals("deedass", found.get().getActor());
        assertEquals("dwdews", found.get().getStudio());
        assertEquals(155, found.get().getLength());
        assertEquals(employee, found.get().getEmployee());
        assertEquals(genre, found.get().getMovieGenre());
        assertEquals(rate, found.get().getMovieRate());
        assertEquals(systemtype, found.get().getSystemType());
        
    }

    @Test
    void B6014223_testNameMustNotBeNull() {
        MovieAdd movieadd = new MovieAdd();
        Employee employee = employeeRepository.findById(1);
        MovieGenre genre = moviegenreRepository.findById(1);
        MovieRate rate = movierateRepository.findById(2);
        SystemType systemtype = systemtypeRepository.findById(2);

        movieadd.setName(null);
        movieadd.setSynopsis("sdggrepddfgtdd");
        movieadd.setDirector("rreoh");
        movieadd.setActor("deedass");
        movieadd.setStudio("dwdews");
        movieadd.setLength(155);
        movieadd.setEmployee(employee);
        movieadd.setMovieGenre(genre);
        movieadd.setMovieRate(rate);
        movieadd.setSystemType(systemtype);

        

        Set<ConstraintViolation<MovieAdd>> result = validator.validate(movieadd);

        // result ต้องมี error 1 ค่าเท่านั้น
         assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<MovieAdd> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }

   @Test
    void B6014223_testSynopsisMustLessThan100Characters() {
        MovieAdd movieadd = new MovieAdd();
        Employee employee = employeeRepository.findById(1);
        MovieGenre genre = moviegenreRepository.findById(1);
        MovieRate rate = movierateRepository.findById(2);
        SystemType systemtype = systemtypeRepository.findById(2);

        movieadd.setName("ROSE");
        movieadd.setSynopsis("sdggweeepekerrteetteerfewrwedwewewdxdgfyjfdwdwwwrwrwedweewerwewswedfrtgyhvcdfredswertrededfggfgftredwswertrertfededrfedlkijuhgodfrvmaserdkyghujujuhuygtgtrfrfrfrfrfrfrfrfrfrfrfrfdededededededrdedredfrdedgtveffrderyuittfv");
        movieadd.setDirector("rreoh");
        movieadd.setActor("deedass");
        movieadd.setStudio("dwdews");
        movieadd.setLength(155);
        movieadd.setEmployee(employee);
        movieadd.setMovieGenre(genre);
        movieadd.setMovieRate(rate);
        movieadd.setSystemType(systemtype);


        Set<ConstraintViolation<MovieAdd>> result = validator.validate(movieadd);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<MovieAdd> v = result.iterator().next();
        assertEquals("Synopsis must be between 10 and 100 characters", v.getMessage());
        assertEquals("synopsis", v.getPropertyPath().toString());
    }

    @Test
    void B6014223_testSynopsisMustMoreThan10Characters() {
        MovieAdd movieadd = new MovieAdd();
        Employee employee = employeeRepository.findById(1);
        MovieGenre genre = moviegenreRepository.findById(1);
        MovieRate rate = movierateRepository.findById(2);
        SystemType systemtype = systemtypeRepository.findById(2);

        movieadd.setName("ROSE");
        movieadd.setSynopsis("sdggr");
        movieadd.setDirector("rreoh");
        movieadd.setActor("deedass");
        movieadd.setStudio("dwdews");
        movieadd.setLength(155);
        movieadd.setEmployee(employee);
        movieadd.setMovieGenre(genre);
        movieadd.setMovieRate(rate);
        movieadd.setSystemType(systemtype);


        Set<ConstraintViolation<MovieAdd>> result = validator.validate(movieadd);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<MovieAdd> v = result.iterator().next();
        assertEquals("Synopsis must be between 10 and 100 characters", v.getMessage());
        assertEquals("synopsis", v.getPropertyPath().toString());
    }

    @Test
    void B6014223_testLengthMustNotBeLessThan60() {
        MovieAdd movieadd = new MovieAdd();
        Employee employee = employeeRepository.findById(1);
        MovieGenre genre = moviegenreRepository.findById(1);
        MovieRate rate = movierateRepository.findById(2);
        SystemType systemtype = systemtypeRepository.findById(2);

        movieadd.setName("ROSE");
        movieadd.setSynopsis("sdggrepddfgtdd");
        movieadd.setDirector("rreoh");
        movieadd.setActor("deedass");
        movieadd.setStudio("dwdews");
        movieadd.setLength(55);
        movieadd.setEmployee(employee);
        movieadd.setMovieGenre(genre);
        movieadd.setMovieRate(rate);
        movieadd.setSystemType(systemtype);

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
        Employee employee = employeeRepository.findById(1);
        MovieGenre genre = moviegenreRepository.findById(1);
        MovieRate rate = movierateRepository.findById(2);
        SystemType systemtype = systemtypeRepository.findById(2);

        movieadd.setName("ROSE");
        movieadd.setSynopsis("sdggrepddfgtdd");
        movieadd.setDirector("rreoh");
        movieadd.setActor("deedass");
        movieadd.setStudio("dwdews");
        movieadd.setLength(550);
        movieadd.setEmployee(employee);
        movieadd.setMovieGenre(genre);
        movieadd.setMovieRate(rate);
        movieadd.setSystemType(systemtype);

        Set<ConstraintViolation<MovieAdd>> result = validator.validate(movieadd);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<MovieAdd> v = result.iterator().next();
        assertEquals("length should not be greater than 500", v.getMessage());
        assertEquals("length", v.getPropertyPath().toString());
    }
    @Test
    void B6014223_testEmployeeMustNotBeNull() {
        MovieAdd movieadd = new MovieAdd();
        MovieGenre genre = moviegenreRepository.findById(1);
        MovieRate rate = movierateRepository.findById(2);
        SystemType systemtype = systemtypeRepository.findById(2);

        movieadd.setName("ROSE");
        movieadd.setSynopsis("sdggrepddfgtdd");
        movieadd.setDirector("rreoh");
        movieadd.setActor("deedass");
        movieadd.setStudio("dwdews");
        movieadd.setLength(155);
        movieadd.setEmployee(null);
        movieadd.setMovieGenre(genre);
        movieadd.setMovieRate(rate);
        movieadd.setSystemType(systemtype);

        

        Set<ConstraintViolation<MovieAdd>> result = validator.validate(movieadd);

        // result ต้องมี error 1 ค่าเท่านั้น
         assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<MovieAdd> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("employee", v.getPropertyPath().toString());
    }

    @Test
    void B6014223_testMovieGenreMustNotBeNull() {
        MovieAdd movieadd = new MovieAdd();
        Employee employee = employeeRepository.findById(1);
        MovieRate rate = movierateRepository.findById(2);
        SystemType systemtype = systemtypeRepository.findById(2);

        movieadd.setName("ROSE");
        movieadd.setSynopsis("sdggrepddfgtdd");
        movieadd.setDirector("rreoh");
        movieadd.setActor("deedass");
        movieadd.setStudio("dwdews");
        movieadd.setLength(155);
        movieadd.setEmployee(employee);
        movieadd.setMovieGenre(null);
        movieadd.setMovieRate(rate);
        movieadd.setSystemType(systemtype);

        

        Set<ConstraintViolation<MovieAdd>> result = validator.validate(movieadd);

        // result ต้องมี error 1 ค่าเท่านั้น
         assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<MovieAdd> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("genre", v.getPropertyPath().toString());
    }
    @Test
    void B6014223_testMovieRateMustNotBeNull() {
        MovieAdd movieadd = new MovieAdd();
        Employee employee = employeeRepository.findById(1);
        MovieGenre genre = moviegenreRepository.findById(1);
        SystemType systemtype = systemtypeRepository.findById(2);

        movieadd.setName("ROSE");
        movieadd.setSynopsis("sdggrepddfgtdd");
        movieadd.setDirector("rreoh");
        movieadd.setActor("deedass");
        movieadd.setStudio("dwdews");
        movieadd.setLength(155);
        movieadd.setEmployee(employee);
        movieadd.setMovieGenre(genre);
        movieadd.setMovieRate(null);
        movieadd.setSystemType(systemtype);

        

        Set<ConstraintViolation<MovieAdd>> result = validator.validate(movieadd);

        // result ต้องมี error 1 ค่าเท่านั้น
         assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<MovieAdd> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("rate", v.getPropertyPath().toString());
    }
    @Test
    void B6014223_testSystemTypeMustNotBeNull() {
        MovieAdd movieadd = new MovieAdd();
        Employee employee = employeeRepository.findById(1);
        MovieGenre genre = moviegenreRepository.findById(1);
        MovieRate rate = movierateRepository.findById(2);

        movieadd.setName("ROSE");
        movieadd.setSynopsis("sdggrepddfgtdd");
        movieadd.setDirector("rreoh");
        movieadd.setActor("deedass");
        movieadd.setStudio("dwdews");
        movieadd.setLength(155);
        movieadd.setEmployee(employee);
        movieadd.setMovieGenre(genre);
        movieadd.setMovieRate(rate);
        movieadd.setSystemType(null);

        

        Set<ConstraintViolation<MovieAdd>> result = validator.validate(movieadd);

        // result ต้องมี error 1 ค่าเท่านั้น
         assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<MovieAdd> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("systemtype", v.getPropertyPath().toString());
    }

}