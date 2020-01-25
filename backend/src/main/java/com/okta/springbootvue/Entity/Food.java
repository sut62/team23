package com.okta.springbootvue.Entity;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Collection; 
import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name="FOOD")
public class Food {
	@Id
	@SequenceGenerator(name="food_seq",sequenceName="food_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="food_seq")
	@Column(name="FOOD_ID",unique = true, nullable = true)
	@NotNull
	private Long id;


	@NotNull
	private String name;

	@NotNull
	@Max(value = 200, message = "Food Price should not be greater than 200")
	@Min(value = 30, message = "Food Price should not be less than 30")
	private int price;

	@OneToMany(fetch = FetchType.EAGER)
	// mappedBy  = "rentCustomer"
	private Collection<Foodanddrink> buy;
}
