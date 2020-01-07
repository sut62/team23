package com.okta.springbootvue.Repository;

import com.okta.springbootvue.Entity.MovieTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MovieTimeRepository extends JpaRepository<MovieTime, Long> {
    MovieTime findById(long idT);
}