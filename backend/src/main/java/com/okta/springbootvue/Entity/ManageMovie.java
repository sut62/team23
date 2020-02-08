package com.okta.springbootvue.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Data
@Entity
@NoArgsConstructor
@Table(name = "ManageMovie")
public class ManageMovie {

    @Id
    @SequenceGenerator(name = "managemovie_seq", sequenceName = "managemovie_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "managemovie_seq")
    @Column(name = "managemovie_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = MovieAdd.class)
    @JoinColumn(name = "MOVIE_ID", insertable = true)
    private MovieAdd movie;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Dub.class)
    @JoinColumn(name = "DUB_ID", insertable = true)
    private Dub lan;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Room.class)
    @JoinColumn(name = "ROOM_ID", insertable = true)
    private Room ro;

    @NotNull
    @Temporal(TemporalType.DATE)
    private  Date date;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = MovieTime.class)
    @JoinColumn(name = "Time_ID", insertable = true)
    private MovieTime time;

    @Size( max = 30 ,message = "must be less than or equal 30 characters")
    private String note;

}