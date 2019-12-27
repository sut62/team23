package com.okta.springbootvue.Repository;

import com.okta.springbootvue.Entity.Food;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface FoodRepository extends JpaRepository<Food, Long> {
	Food findById(long id);
}