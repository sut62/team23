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
public class MovieGenre{
    @Id
    @SequenceGenerator(name="genreseq",sequenceName="genreseq") 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="genreseq")
      private @NonNull Long genre_id;
      @NotNull
      private String genre;

      public void setMovieGenre_id(Long genre_id){
        this.genre_id=genre_id;
      }
      public Long getMovieGenre_id(){
        return genre_id;
      }
      
      public void setMovieGenre(String genre){
        this.genre = genre;
      }
      public String getMovieGenre(){
        return genre;
      }

}
