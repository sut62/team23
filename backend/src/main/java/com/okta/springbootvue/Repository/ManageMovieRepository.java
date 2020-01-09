package com.okta.springbootvue.Repository;

import com.okta.springbootvue.Entity.ManageMovie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ManageMovieRepository extends JpaRepository<ManageMovie, Long> {
    ManageMovie findById(long id);
}