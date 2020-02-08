package com.okta.springbootvue.Entity;

import lombok.*;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.okta.springbootvue.Entity.TicketBooking;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.FetchType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;
// import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.JoinColumn;

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
    
    // @JoinColumn(name="PAYMENT_DATE")
    @NotNull
    private  Date paymentDate;

    @Pattern(regexp = "\\d{16}")
    @NotNull
    private String cardNumber;

    @Pattern(regexp = "\\d{3}")
    @NotNull
    private String securityCode;

    @Size (max=50,message="should not greater than 50")
    @NotNull
    private String cardHolderName;

    @JsonIgnoreProperties("payment")
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "TicketBooking_ID", nullable = false,unique = true)
    private @NotNull TicketBooking ticketBooking;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = CardType.class)
    @JoinColumn(name = "CARDTYPE_ID", insertable = true)
    private @NotNull CardType cardType;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Bank.class)
    @JoinColumn(name = "BANK_ID", insertable = true)
    private @NotNull Bank bank;

    



}