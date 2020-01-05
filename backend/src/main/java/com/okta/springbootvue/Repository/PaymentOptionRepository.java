package com.okta.springbootvue.Repository;



import com.okta.springbootvue.Entity.PaymentOption;

import org.springframework.data.jpa.repository.JpaRepository;  
import org.springframework.data.rest.core.annotation.RepositoryRestResource;  
  
@RepositoryRestResource

public interface PaymentOptionRepository extends JpaRepository<PaymentOption, Long> {

    PaymentOption findById(long id);
}