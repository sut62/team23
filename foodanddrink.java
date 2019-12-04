package com.cpe.backend.Foodanddrink.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.cpe.backend.Appointment.entity.Department;
import com.cpe.backend.RegisterPatient.entity.Patient;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;;

@Data
@Entity
@NoArgsConstructor
@Table(name="FOODANDDRINK")
public class Foodanddrink {

    @Id
    @SequenceGenerator(name="foodanddrink_seq",sequenceName="foodanddrink_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="foodanddrink_seq")
   
    @Column(name = "FOODANDDRINK_ID", unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull Date Date;

    @Getter @Setter
    @OneToOne(fetch = FetchType.EAGER, targetEntity = customer.class)     
    @JoinColumn(name = "Customer_ID", insertable = true)     
    private Customer customer; 

    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = food.class)     
    @JoinColumn(name = "Food_ID", insertable = true)     
    private Food food; 
 
    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = drink.class)     
    @JoinColumn(name = "Drink_ID", insertable = true)     
    private Drink drink;
}