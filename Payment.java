package com.cpe.backend.Entity;

import lombok.*;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.cpe.backend.Entity.TicketBooking;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.FetchType;

//import java.util.Date;
// import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
// import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@NoArgsConstructor
@Data
@Entity
@Table(name = "PAYMENT")
public class Payment {
    @Id
    @SequenceGenerator(name = "PAYMENT_SEQ", sequenceName = "PAYMENT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PAYMENT_SEQ")
    @Column(name = "PAYMENT_ID", unique = true, nullable = true)
    private @NonNull Long id;
    // private @NonNull Long totalPrice;

    @JsonIgnoreProperties("payment")
    //@JsonManagedReference
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "TicketBooking_ID", nullable = false,unique = true)
    private TicketBooking ticketbooking;



    @ManyToOne(fetch = FetchType.EAGER, targetEntity = PaymentOption.class)
    @JoinColumn(name = "PAYMENTOPTION_ID", insertable = true)
    private PaymentOption paymentOption;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = TypeBank.class)
    @JoinColumn(name = "TYPEBANK_ID", insertable = true)
    private TypeBank typeBank;




}