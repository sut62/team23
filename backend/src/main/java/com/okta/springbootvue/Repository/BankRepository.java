package com.okta.springbootvue.Repository;
import com.okta.springbootvue.Entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;  
import org.springframework.data.rest.core.annotation.RepositoryRestResource;  
  
@RepositoryRestResource
public interface  BankRepository extends JpaRepository<Bank, Long> {
    
    Bank findById(long id);
}