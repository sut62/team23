package com.okta.springbootvue.Entity;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
@Entity

public class User{
    @Id
    @SequenceGenerator(name="userseq",sequenceName="userseq") 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="userseq")
        private Long id;
        private String username;
        private String password;
        private String name;
        private String tel;
        private String email;
        private String age;


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

  public void serUsername(String username){
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
}
