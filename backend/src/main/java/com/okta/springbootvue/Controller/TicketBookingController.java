package com.okta.springbootvue.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;


import com.okta.springbootvue.Entity.*;
import com.okta.springbootvue.Repository.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class TicketBookingController {
    @Autowired
    private final TicketBookingRepository ticketBookingRepository;
    @Autowired
    private ManageMovieRepository manageMovieRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SeatTypeRepository seatTypeRepository;

    TicketBookingController(TicketBookingRepository ticketBookingRepository) {
        this.ticketBookingRepository = ticketBookingRepository;
    }

    @GetMapping("/ticketBooking")
    public Collection<TicketBooking> ticketBookings() {
        return ticketBookingRepository.findAll().stream().collect(Collectors.toList());
    }
    /*
    @GetMapping("/ticketBooking/check/{type}")
        public TicketBooking checkTicketBooking(@PathVariable String type){
        return ticketBookingRepository.findByType(type);
    }   */
    @GetMapping("/ticketBooking/{id}")
    public Optional<TicketBooking> TicketBookigs(@PathVariable Long id) {
        Optional<TicketBooking> ticketBooking = ticketBookingRepository.findById(id);
        return ticketBooking;
    }

    
    @PostMapping("/ticketBooking/{user_id}/{movie_id}/{movie_date}/{movie_time}/{seat}/{note}")
    public TicketBooking newTicketBookingl(TicketBooking newTicketBooking,
    @PathVariable long user_id,
    @PathVariable long movie_id,
    @PathVariable long movie_date,
    @PathVariable long movie_time,
    @PathVariable long seat,
    @PathVariable String note) {
        
   

   
   
    User user = userRepository.findById(user_id);
    ManageMovie movie = manageMovieRepository.findById(movie_id);
    ManageMovie date = manageMovieRepository.findById(movie_date);
    ManageMovie time = manageMovieRepository.findById(movie_time);
    SeatType type = seatTypeRepository.findById(seat);
    
   

    newTicketBooking.setUser(user);
    newTicketBooking.setMovie(movie); 
    newTicketBooking.setDate(date);
    newTicketBooking.setTime(time);
    newTicketBooking.setType(type);
    newTicketBooking.setBookingDate(new Date()); 
    

    return ticketBookingRepository.save(newTicketBooking); //บันทึก Objcet ชื่อ TicketBooking
    
    }
}