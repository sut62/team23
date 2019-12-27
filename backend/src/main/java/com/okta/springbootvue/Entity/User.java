package com.okta.springbootvue.Entity;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import lombok.*;
@Entity

public class User{
    @Id
    @SequenceGenerator(name="userseq",sequenceName="userseq") 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="userseq")
        private @NonNull Long id;
        private @NonNull String username;
        private @NonNull String password;
        private @NonNull String name;
        private @NonNull String tel;
        private @NonNull String email;
        private @NonNull String age;


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

  public void setEmail(String email){
    this.email=email;
  }
  public String getEmail(){
    return email;
  }

  public void setAge(String age){
    this.age=age;
  }
  public String getAge(){
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
