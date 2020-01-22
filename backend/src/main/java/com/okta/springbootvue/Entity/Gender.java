package com.okta.springbootvue.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.*;

@Entity
public class Gender{
    @Id
    @SequenceGenerator(name="genseq",sequenceName="genseq") 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="genseq")  
    private Long gender_id;

    
    @NotNull 
    @Size(min = 1,max = 5,message = "size must be between 1 and 5")
    private String gender_name;

    public void setGender_id(Long gender_id){
        this.gender_id= gender_id;
    }
    public Long getGender_id(){
        return gender_id;
    }

    public void setGender_name(String gender_name){
        this.gender_name = gender_name;
    }
    public String getGender_name(){
        return gender_name;
    }
    
}
