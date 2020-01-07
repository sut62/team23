package com.okta.springbootvue.Entity;
import lombok.*;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Collection;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Data
@Entity
@NoArgsConstructor
@Table(name="MovieTime")
public class MovieTime {
	@Id
	@SequenceGenerator(name="time_seq",sequenceName="time_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="time_seq")
	@Column(name="Time_ID",unique = true, nullable = true)
	private @NonNull Long idT;
	private @NonNull String time;
	
	@OneToMany(fetch = FetchType.EAGER)
	private Collection<ManageMovie> clock;

}