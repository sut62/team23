package com.okta.springbootvue.Repository;


import com.okta.springbootvue.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findById(long employee_id);
    Employee findByUsername(String username);
    Employee findByUsernameAndPassword(String username, String password);
}