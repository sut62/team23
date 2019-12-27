package com.okta.springbootvue.Repository;


import com.okta.springbootvue.Entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface GenderRepository extends JpaRepository<Gender, Long> {
   Gender findById(long gender_id);

}