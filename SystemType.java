package com.okta.entity;
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
@Table(name="SYSTEMTYPE")
public class SystemType {
@Id
@SequenceGenerator(name="SYSTEMTYPE_SEQ",sequenceName="SYSTEMTYPE_SEQ")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="MOVIEGENRE_SEQ")
@Column(name="SYSTEMTYPE_ID",unique = true, nullable = true)
private @NonNull Long id;

private @NonNull String name;

@OneToMany(fetch = FetchType.EAGER)
private Collection<MovieAdd> movieadd;
}