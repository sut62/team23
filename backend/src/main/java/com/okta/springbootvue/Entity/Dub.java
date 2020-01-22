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

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name="Dub")
public class Dub {
	@Id
	@SequenceGenerator(name="language_seq",sequenceName="language_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="language_seq")
	@Column(name="Dub_ID",unique = true, nullable = true)
	private @NonNull Long idL;

	@NotNull
	private String langauge;
	
	@OneToMany(fetch = FetchType.EAGER)
	private Collection<ManageMovie> lan;

}