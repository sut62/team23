package com.okta.springbootvue.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import lombok.*;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Data
@NoArgsConstructor

public class MovieAdd{
    @Id
    @SequenceGenerator(name="addseq",sequenceName="addseq") 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="addseq")
        private @NonNull Long id;

        @NotNull
        private  String name;
        
        @NotNull
        @Size(min = 10, max = 100, message = "Synopsis must be between 10 and 100 characters")
        private String synopsis;

        private @NotNull String director;

        private @NotNull String actor;

        private @NotNull String studio;

        @Min(value = 60, message = "length should not be less than 60")
        @Max(value = 500, message = "length should not be greater than 500")
        private @NotNull int length;
        
@ManyToOne
private @NotNull Employee employee;
@ManyToOne
  private @NotNull MovieGenre genre;
@OneToOne
  private @NotNull MovieRate rate;
@ManyToOne
private @NotNull SystemType systemtype;

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

  public void setLength(int length){
    this.length=length;
  }
  public int getLength(){ 
    return length;
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