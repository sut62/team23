package com.okta.springbootvue.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Data
@Entity
@NoArgsConstructor
@Table(name="ManageMovie")
public class ManageMovie {

    @Id
    @SequenceGenerator(name="managemovie_seq",sequenceName="managemovie_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="managemovie_seq")
    @Column(name = "managemovie_ID", unique = true, nullable = true)
    private @NonNull Long id;
    @Column(name = "MovieDay", unique = true, nullable = true)
    private @NonNull Date Day;
    @Column(name = "MovieTime", unique = true, nullable = true)
    private @NonNull Date Time;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Movie.class)
    @JoinColumn(name = "MOVIE_ID", insertable = true)
    private Movie name;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Dub.class)
    @JoinColumn(name = "DUB_ID", insertable = true)
    private Dub lan;

    

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Room.class)
    @JoinColumn(name = "ROOM_ID", insertable = true)
    private Room ro;




}