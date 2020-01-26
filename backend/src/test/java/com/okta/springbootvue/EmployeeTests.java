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
public class EmployeeTests {

    private Validator validator;

    @Autowired
    private EmployeeRepository employeeRepository ;


    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

     @Test
    void B6014223_testEmployeeOk() {
        Employee employee = new Employee();
        employee.setEmployee("สุบรรชา สวัสดี");
        
        

        employee = employeeRepository.saveAndFlush(employee);
        
        Optional<Employee> found = employeeRepository.findById(employee.getEmployee_id());
        assertEquals("สุบรรชา สวัสดี", found.get().getEmployee());
        
    }
    @Test
    void B6014223_testEmployeeMustNotBeNull() {
        Employee employee = new Employee();
        employee.setEmployee(null);

        

        Set<ConstraintViolation<Employee>> result = validator.validate(employee);

        // result ต้องมี error 1 ค่าเท่านั้น
         assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Employee> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("employee", v.getPropertyPath().toString());
    }

}