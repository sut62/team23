package com.okta.springbootvue.Entity;

import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import lombok.*;
@Entity
@Data
@NoArgsConstructor

public class User{
    @Id
    @SequenceGenerator(name="userseq",sequenceName="userseq") 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="userseq")
        private @NonNull Long id;
        private @NonNull String username;
        private @NonNull String password;
        private @NonNull String name;
        
        @NotNull
        @Size(min = 10, max = 10, message = "tel must be between 10 and 10 characters")
        private String tel;
        
        @NotNull
        @Max(value = 100, message = "age should not be greater than 100")
        private  int age;


@ManyToOne
private Country country;
@ManyToOne
  private Gender gender;
@ManyToOne
  private Jobs jobs;
  
  public void setId(Long id){
    this.id = id;
  }
  public Long getId(){
    return id;
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

  public void setName(String name){
    this.name=name;
  }
  public String getName(){
    return name;
  }

  public void setTel(String tel){
    this.tel=tel;
  }
  public String getTel(){
    return tel;
  }

  public void setAge(int age){
    this.age=age;
  }
  public int getAge(){
    return age;
  }

  public void setGender(Gender gender){
    this.gender = gender;
  }
  public Gender getGender(){
    return gender;
  }

  public void setJobs(Jobs jobs){
    this.jobs = jobs;
  }
  public Jobs getJobs(){
    return jobs;
  }

  public void setCountry(Country country){
    this.country = country;
  }
  public Country getCountry(){
    return country;
  }
}
