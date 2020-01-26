package com.okta.springbootvue.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Entity
@Data
@NoArgsConstructor
@Table(name="TicketBooking")
public class TicketBooking {

    @Id
    @SequenceGenerator(name="ticket_booking_seq",sequenceName="ticket_booking_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ticket_booking_seq")
    @Column(name = "TicketBooking_ID", unique = true, nullable = true)
    @NotNull
    private Long id;

    
    
    @Size(min =2 ,max = 50, message = "Note must  between 2 and 50 characters")
    //@Column(name = "Note")
    private String note;



     
    @NotNull
    @Column(name = "Booking_Date")
    private Date bookingDate;
    



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


    /*@OneToOne(fetch = FetchType.EAGER, targetEntity = SeatType.class)
    @JoinColumn(name = "PRICE", insertable = true)
    private SeatType price;*/

    


	
	}



	



