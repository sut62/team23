package com.okta.springbootvue.Entity;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
	private @NonNull Long id;
	private @NonNull String name;
	private @NonNull int price;

	@OneToMany(fetch = FetchType.EAGER)
	// mappedBy  = "rentCustomer"
	private Collection<Foodanddrink> buy;
}
