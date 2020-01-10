package com.okta.springbootvue.Repository;

import com.okta.springbootvue.Entity.Foodanddrink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface FoodanddrinkRepository extends JpaRepository<Foodanddrink, Long> {
}