package com.okta.springbootvue.Entity;

//import lombok.*;

import javax.persistence.Id;
//import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.print.event.PrintServiceAttributeEvent;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;
import lombok.NoArgsConstructor;

//import javax.persistence.*;


//import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Entity
@NoArgsConstructor
@Table(name="SEAT_TYPE")
public class SeatType {
    @Id
    @SequenceGenerator(name="seat_type_seq",sequenceName="seat_type_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seat_type_seq")
    @Column(name="SEAT_TYPE_ID",unique = true, nullable = true)
    @NotNull
    private Long id;


    @NotNull
    @Pattern(regexp = "[AB]\\d{1}")
    @Column(name = "Seat")
    private  String seat;

 
   @Max(value = 300, message = "Price should not be greater than 300")
   @Column(name = "Price")
   private int price;



    



}