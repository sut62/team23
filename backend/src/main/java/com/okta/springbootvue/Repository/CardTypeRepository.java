package com.okta.springbootvue.Repository;



import com.okta.springbootvue.Entity.CardType;

import org.springframework.data.jpa.repository.JpaRepository;  
import org.springframework.data.rest.core.annotation.RepositoryRestResource;  
  
@RepositoryRestResource

public interface CardTypeRepository extends JpaRepository<CardType, Long> {

    CardType findById(long id);
}