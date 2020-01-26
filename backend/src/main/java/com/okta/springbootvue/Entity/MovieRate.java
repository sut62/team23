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
public class MovieRate{
    @Id
    @SequenceGenerator(name="rateseq",sequenceName="rateseq") 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="rateseq")
      private @NonNull Long rate_id;
      @NotNull 
      private String rate;

      public void setMovieRate_id(Long rate_id){
        this.rate_id=rate_id;
      }
      public Long getMovieRate_id(){
        return rate_id;
      }
      
      public void setMovieRate(String rate){
        this.rate = rate;
      }
      public String getMovieRate(){
        return rate;
      }

}
