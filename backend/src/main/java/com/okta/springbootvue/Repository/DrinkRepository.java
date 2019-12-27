package com.okta.springbootvue.Repository;

import com.okta.springbootvue.Entity.Drink;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface DrinkRepository extends JpaRepository<Drink, Long> {
    Drink findById(long id);
}