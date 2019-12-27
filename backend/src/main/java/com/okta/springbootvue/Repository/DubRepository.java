package com.okta.springbootvue.Repository;

import com.okta.springbootvue.Entity.Dub;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DubRepository extends JpaRepository<Dub, Long> {
    Dub findById(long idL);
}