package com.okta.springbootvue.Entity;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
public class Country{
    @Id
    @SequenceGenerator(name="conseq",sequenceName="conseq") 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="conseq")
      private @NonNull Long country_id;
      private @NonNull String country;

      public void setCountry_id(Long country_id){
        this.country_id=country_id;
      }
      public Long getCountry_id(){
        return country_id;
      }
      
      public void setCountry(String country){
        this.country = country;
      }
      public String getCountry(){
        return country;
      }

}
