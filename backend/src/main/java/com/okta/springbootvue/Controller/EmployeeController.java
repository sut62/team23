package com.okta.springbootvue.Controller;



import com.okta.springbootvue.Entity.Employee;
import com.okta.springbootvue.Repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/employee")
    public Collection<Employee> Employees () {
        return employeeRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/employee/{username}/{password}")
    public Employee employeeLogin(@PathVariable String username, @PathVariable String password){
        return employeeRepository.findByUsernameAndPassword(username,password);}

        @GetMapping("/employee/{employee_id}")
    public Employee employeeId(@PathVariable long employee_id){
        return employeeRepository.findById(employee_id);
    }
}




