package com.okta.springbootvue.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;




import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Data
@Entity
@NoArgsConstructor
@Table(name="TicketBooking")
public class TicketBooking {

    @Id
    @SequenceGenerator(name="ticket_booking_seq",sequenceName="ticket_booking_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ticket_booking_seq")
    @Column(name = "Ticket_Booking_ID", unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull Date bookingDate;
    
    
   
    

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinColumn(name = "USER_ID", insertable = true)
    private User user;


    @ManyToOne(fetch = FetchType.EAGER, targetEntity = ManageMovie.class)
    @JoinColumn(name = "DATE", insertable = true)
    private ManageMovie date;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = ManageMovie.class)
    @JoinColumn(name = "MOVIE_ID", insertable = true)
    private ManageMovie movie;


    @ManyToOne(fetch = FetchType.EAGER, targetEntity = ManageMovie.class)
    @JoinColumn(name = "Time_ID", insertable = true)
    private ManageMovie time;



    @OneToOne(fetch = FetchType.EAGER, targetEntity = SeatType.class)
    @JoinColumn(name = "SEAT_TYPE_ID", insertable = true)
    private SeatType type;




}