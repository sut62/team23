package com.okta.springbootvue.Entity;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
@Entity
public class Employee{
    @Id
    @SequenceGenerator(name="employeeseq",sequenceName="employeeseq") 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="employeeseq")
      private @NonNull Long employee_id;

      @NotNull
      private  String employee;
      
      private @NonNull String username;
      private @NonNull String password;

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

      public void setUsername(String username){
        this.username =username;
      }
      public String getUsername(){
        return username;
      }
    
      public void setPassword(String password){
        this.password=password;
      }
      public String getPassword(){
        return password;
      }
      
    //   public Employee(String employee,String username,String password){
    //     this.employee =employee;
    //     this.username=username;
    //     this.password=password;
        

    // }
}