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
public class SystemType{
    @Id
    @SequenceGenerator(name="systemtypeseq",sequenceName="systemtypeseq") 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="systemtypeseq")
      private @NonNull Long systemtype_id;
      @NotNull 
      private String systemtype;

      public void setSystemType_id(Long systemtype_id){
        this.systemtype_id=systemtype_id;
      }
      public Long getSystemType_id(){
        return systemtype_id;
      }
      
      public void setSystemType(String systemtype){
        this.systemtype = systemtype;
      }
      public String getSystemType(){
        return systemtype;
      }

}
