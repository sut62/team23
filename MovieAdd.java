package com.okta.entity;
import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
@Table(name="MOVIEADD")
public class MovieAdd {
@Id
@SequenceGenerator(name="MOVIEADD_SEQ",sequenceName="MOVIEADD_SEQ")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="MOVIEADD_SEQ")
@Column(name="MOVIEADD_ID",unique = true, nullable = true)
private @NonNull Long id;

@Column(name="NAME")
private @NonNull String name;

@Column(name="SYNOPSIS")
private @NonNull String synopsis;

@Column(name="DIRECTOR")
private @NonNull String director;

@Column(name="ACTOR")
private @NonNull String actor;

@Column(name="MOVIESTUDIO")
private @NonNull String studio;

@Column(name="LENGTH")
private @NonNull String length;

@Column(name="RELEASEDATE")
private @NonNull Date release;

@ManyToOne(fetch = FetchType.EAGER, targetEntity = MovieGenre.class)
@JoinColumn(name = "MOVIEGENRE_ID", insertable = true)
private MovieGenre moviegenre;

@OneToOne(fetch = FetchType.EAGER, targetEntity = MovieRate.class)
@JoinColumn(name = "MOVIERATE_ID", insertable = true)
private MovieRate movierate;

@ManyToOne(fetch = FetchType.EAGER, targetEntity = SystemType.class)
@JoinColumn(name = "SYSTEMTYPE_ID", insertable = true)
private SystemType systemtype;
}