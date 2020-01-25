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
@Table(name="Room")
public class Room {
	@Id
	@SequenceGenerator(name="room_seq",sequenceName="room_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="room_seq")
	@Column(name="Room_ID",unique = true, nullable = true)
	private @NonNull Long idR;
	
	@NotNull
	private String room;

	@OneToMany(fetch = FetchType.EAGER)
	private Collection<ManageMovie> theater;


}