package com.okta.springbootvue.Repository;


import com.okta.springbootvue.Entity.MovieRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MovieRateRepository extends JpaRepository<MovieRate, Long> {
    MovieRate findById(long rate_id);

}