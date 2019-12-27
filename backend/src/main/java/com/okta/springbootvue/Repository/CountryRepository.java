package com.okta.springbootvue.Repository;


import com.okta.springbootvue.Entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface CountryRepository extends JpaRepository<Country, Long> {
    Country findById(long country_id);

}