package com.okta.springbootvue.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import com.okta.springbootvue.Entity.User;

@Data
@Entity        
@NoArgsConstructor
@Table(name="TICKETBOOKING")
public class TicketBooking {

    @Id       
    @SequenceGenerator(name="ticket_booking_seq",sequenceName="ticket_booking_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ticket_booking_seq") 
    
    @Column(name = "TICKET_BOOKING_ID", unique = true, nullable = true) 
    private @NonNull Long id;

    
    @Column(name="BOOKING_DATE")           
    private @NonNull Date bookingDate;


    
    @OneToOne
    private SeatType seatType;
    @ManyToOne
      private ManageMovie manageMovie;
    @ManyToOne
      private User user;
	
	
    
	
}