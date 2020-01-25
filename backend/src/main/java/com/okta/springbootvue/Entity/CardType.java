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
@Table(name="CARDTYPE")
public class CardType {
    @Id
    @SequenceGenerator(name="CARDTYPE_SEQ",sequenceName="CARDTYPE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CARDTYPE_SEQ")
    @Column(name="CARDTYPE_ID",unique = true, nullable = true)
   
    private @NonNull Long id;
    
    @Getter@Setter 
    private String name ;  

    
}