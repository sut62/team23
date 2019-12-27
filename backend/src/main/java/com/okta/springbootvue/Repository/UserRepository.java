package com.okta.springbootvue.Repository;


import com.okta.springbootvue.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface UserRepository extends JpaRepository<User, Long> {
    //User findById(long id);

}