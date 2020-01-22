package com.okta.springbootvue.Repository;


import com.okta.springbootvue.Entity.MovieAdd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MovieAddRepository extends JpaRepository<MovieAdd, Long> {
    MovieAdd findById(long id);
    MovieAdd findByName(String name);
}