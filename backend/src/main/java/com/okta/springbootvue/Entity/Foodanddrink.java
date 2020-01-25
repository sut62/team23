package com.okta.springbootvue.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import com.okta.springbootvue.Entity.Employee;

@Data
@Entity
@NoArgsConstructor
@Table(name="FoodAndDrink")
public class Foodanddrink {

    @Id
    @SequenceGenerator(name="FoodAndDrink_seq",sequenceName="FoodAndDrink_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FoodAndDrink_seq")
    @Column(name = "FoodAndDrink_ID", unique = true, nullable = true)
    @NotNull
    private  Long id;
    
    @Size(min = 2, max = 60, message = "Note must be between 2 and 60 characters")
    private String note;
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinColumn(name = "USER_ID", insertable = true)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Drink.class)
    @JoinColumn(name = "DRINK_ID", insertable = true)
    private Drink drink;  

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Food.class)
    @JoinColumn(name = "FOOD_ID", insertable = true)
    private Food food;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    private Employee employee;

    

} 