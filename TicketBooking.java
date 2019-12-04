package com.cpe.backend.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import com.cpe.backend.entity.Customer;
import com.cpe.backend.entity.Movie;

@Data
@Entity        // @Entity ทำให้ public class  เป็น Entity
@NoArgsConstructor
@Table(name="TICKET_BOOKING")
public class TicketBooking {

    @Id         //กำหนดให้ Long id เป็น Primary Key
    @SequenceGenerator(name="ticket_booking_seq",sequenceName="ticket_booking_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ticket_booking_seq") //@GeneratedValue ทำให้ id รันเลขไปอัตโนมัติ

    
    @Column(name = "TICKET_BOOKING_ID", unique = true, nullable = true) //สร้าง column ขื่อ TICKET_BOOKING_ID
    private @NonNull Long id;

   
    @Column(name="BOOKING_DATE")           
    private @NonNull Date bookingDate;


    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Customer.class)
    @JoinColumn(name = "CUSTOMER_ID", insertable = true)
    private Customer createdBy;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Movie.class)
    @JoinColumn(name = "MOVIE_ID", insertable = true)
    private Movie movie;
    
    @OneToMany(fetch = FetchType.EAGER, targetEntity = SeatType.class)
    @JoinColumn(name = "SEAT_TYPE_ID", insertable = true)
    private SeatType seatType;
	
    
	
}