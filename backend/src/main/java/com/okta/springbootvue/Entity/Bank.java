package com.okta.springbootvue.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;


@Entity  
@Data  
@NoArgsConstructor  
@Table(name = "BANK")
public class Bank {  //ผู้ป่วย
      
  @Id 
  @SequenceGenerator(name="bank_seq",sequenceName="bank_seq")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="bank_seq") 
  @Column(name = "BANK_ID", unique = true, nullable = true)
  private @NonNull Long id;  

  
  private String name ;


  @OneToMany(fetch = FetchType.LAZY)
  //mappedBy  = "type"
  private Collection<Payment> payment;








      
}