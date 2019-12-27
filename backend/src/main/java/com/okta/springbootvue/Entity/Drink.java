package com.okta.springbootvue.Entity;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Collection;

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
    private @NonNull Long id;

    private @NonNull String name;

    @OneToMany(fetch = FetchType.EAGER)
    // mappedBy  = "createdBy"
    private Collection<Foodanddrink> buy;
}