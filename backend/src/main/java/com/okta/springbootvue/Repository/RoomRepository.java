package com.okta.springbootvue.Repository;

import com.okta.springbootvue.Entity.Room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findById(long idR);
}