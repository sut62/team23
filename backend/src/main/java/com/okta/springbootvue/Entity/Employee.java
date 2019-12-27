package com.okta.springbootvue.Entity;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
public class Employee{
    @Id
    @SequenceGenerator(name="employeeseq",sequenceName="employeeseq") 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="employeeseq")
      private @NonNull Long employee_id;
      private @NonNull String employee;

      public void setEmployee_id(Long employee_id){
        this.employee_id=employee_id;
      }
      public Long getEmployee_id(){
        return employee_id;
      }
      
      public void setEmployee(String employee){
        this.employee = employee;
      }
      public String getEmployee(){
        return employee;
      }

}
