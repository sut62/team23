package com.okta.springbootvue.Repository;
import com.okta.springbootvue.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;  
import org.springframework.data.rest.core.annotation.RepositoryRestResource;  
  
@RepositoryRestResource
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findById(long id);

}