package com.okta.springbootvue.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;

import java.util.Collection;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
@Data
@Entity
@NoArgsConstructor
@Table(name="PAYMENTOPTION")
public class PaymentOption {
    @Id
    @SequenceGenerator(name="PAYMENTOPTION_SEQ",sequenceName="PAYMENTOPTION_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PAYMENTOPTION_SEQ")
    @Column(name="PAYMENTOPTION_ID",unique = true, nullable = true)
   
    private @NonNull Long id;
    
    @Getter@Setter 
    private String name ;  

    
}