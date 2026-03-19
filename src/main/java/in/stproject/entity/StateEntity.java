package in.stproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;



@Entity
@Table(name = "state_master")
@Setter
@Getter
public class StateEntity {

	
	
	@Id
	private Integer stateId;
	private String stateName;
	
	@ManyToOne
	@JoinColumn(name = "country_id")
	private CountryEntity country;
	
}
