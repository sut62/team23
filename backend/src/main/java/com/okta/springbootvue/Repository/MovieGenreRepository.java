package com.okta.springbootvue.Repository;


import com.okta.springbootvue.Entity.MovieGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MovieGenreRepository extends JpaRepository<MovieGenre, Long> {
    MovieGenre findById(long genre_id);

}