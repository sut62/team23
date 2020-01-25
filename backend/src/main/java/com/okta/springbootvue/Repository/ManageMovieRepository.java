package com.okta.springbootvue.Repository;

import java.util.Collection;
import java.util.Date;

import com.okta.springbootvue.Entity.ManageMovie;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ManageMovieRepository extends JpaRepository<ManageMovie, Long> {
    ManageMovie findById(long id);

    /*@Query(value = "SELECT * FROM MANAGE_MOVIE where ro = :room_id and  time = :movie_time  and date = :movie_day " ,nativeQuery = true)
    Collection<ManageMovie> findByData(@Param("room_id") Long room_id ,@Param("movie_time") Long movie_time ,@Param("movie_day") Date movie_day );*/

    
}