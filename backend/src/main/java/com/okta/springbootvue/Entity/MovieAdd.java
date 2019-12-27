package com.okta.springbootvue.Entity;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import lombok.*;
@Entity

public class MovieAdd{
    @Id
    @SequenceGenerator(name="addseq",sequenceName="addseq") 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="addseq")
        private @NonNull Long id;
        private @NonNull String name;
        private @NonNull String synopsis;
        private @NonNull String director;
        private @NonNull String actor;
        private @NonNull String studio;
        private @NonNull String length;
        private @NonNull Date release;


@ManyToOne
private Employee employee;
@ManyToOne
  private MovieGenre genre;
@OneToOne
  private MovieRate rate;
@ManyToOne
private SystemType systemtype;
  
  public void setId(Long id){
    this.id = id;
  }
  public Long getId(){
    return id;
  }

  public void setName(String name){
    this.name =name;
  }
  public String getName(){
    return name;
  }

  public void setSynopsis(String synopsis){
    this.synopsis=synopsis;
  }
  public String getSynopsis(){
    return synopsis;
  }

  public void setDirector(String director){
    this.director=director;
  }
  public String getDirector(){
    return director;
  }

  public void setActor(String actor){
    this.actor=actor;
  }
  public String getActor(){
    return actor;
  }

  public void setStudio(String studio){
    this.studio=studio;
  }
  public String getStudio(){
    return studio;
  }

  public void setLength(String length){
    this.length=length;
  }
  public String getLength(){
    return length;
  }

  public void setRelease(Date release){
    this.release = release;
  }
  public Date getRelease(){
    return release;
  }

  public void setEmployee(Employee employee){
    this.employee = employee;
  }
  public Employee getEmployee(){
    return employee;
  }

  public void setMovieGenre(MovieGenre genre){
    this.genre = genre;
  }
  public MovieGenre getMovieGenre(){
    return genre;
  }

  public void setMovieRate(MovieRate rate){
    this.rate = rate;
  }
  public MovieRate getMovieRate(){
    return rate;
  }

  public void setSystemType(SystemType systemtype){
    this.systemtype = systemtype;
  }
  public SystemType getSystemType(){
    return systemtype;
  }
}
