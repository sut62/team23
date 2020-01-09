package com.okta.springbootvue.Repository;


import com.okta.springbootvue.Entity.TicketBooking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface TicketBookingRepository extends JpaRepository<TicketBooking, Long> {
    TicketBooking findById(long id);
}