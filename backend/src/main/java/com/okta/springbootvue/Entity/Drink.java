package com.okta.springbootvue.Entity;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Collection;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue; 
import javax.persistence.GenerationType;

@Data
@Entity
@NoArgsConstructor
@Table(name="DRINK")
public class Drink {
    @Id
    @SequenceGenerator(name="DRINK_SEQ",sequenceName="DRINK_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="DRINK_SEQ")
    @Column(name="DRINK_ID",unique = true, nullable = true)
    @NotNull
    private Long id;

    @NotNull
    private  String name;
    
    @NotNull
	@Max(value = 100, message = "Drink Price should not be greater than 100")
	@Min(value = 20, message = "Drink Price should not be less than 20")
    private @NonNull int price;

    @OneToMany(fetch = FetchType.EAGER)
    // mappedBy  = "createdBy"
    private Collection<Foodanddrink> buy;
}