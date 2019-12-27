package com.okta.springbootvue.Repository;


import com.okta.springbootvue.Entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface JobsRepository extends JpaRepository<Jobs, Long> {
    Jobs findById(long job_id);

}