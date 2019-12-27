package com.okta.springbootvue.Repository;


import com.okta.springbootvue.Entity.SystemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SystemTypeRepository extends JpaRepository<SystemType, Long> {
    SystemType findById(long systemtype_id);

}