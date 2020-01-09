package com.okta.springbootvue.Repository;


import com.okta.springbootvue.Entity.SeatType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface SeatTypeRepository extends JpaRepository<SeatType, Long> {
	SeatType findById(long id);
}