package com.okta.springbootvue.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.*;
@Entity
public class Gender{
    @Id
    @SequenceGenerator(name="genseq",sequenceName="genseq") 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="genseq")  
    private Long gender_id;
    private @NonNull String gender;

    public void setGender_id(Long gender_id){
        this.gender_id= gender_id;
    }
    public Long getGender_id(){
        return gender_id;
    }

    public void setGender(String gender){
        this.gender = gender;
    }
    public String getGender(){
        return gender;
    }
    
}
