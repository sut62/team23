package com.okta.springbootvue.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.*;
@Entity
public class Jobs{
    @Id
    @SequenceGenerator(name="jobseq",sequenceName="jobseq") 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="jobseq")  
    private  @NonNull Long job_id;
    private @NonNull String job;

    public void setJob_id(Long job_id){
        this.job_id =job_id;
    }
    public Long getJob_id(){
        return job_id;
    }

    public void setJob(String job){
        this.job=job;
    }
    public String getJob(){
        return job;
    }
}
