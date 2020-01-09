package com.okta.springbootvue.Entity;

import lombok.*;

import javax.persistence.Id;
//import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
    private @NonNull Long id;
    private @NonNull String seat;



}