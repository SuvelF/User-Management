package in.stproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "country_master")
@Setter
@Getter
public class CountryEntity {

	@Id
	private Integer countryId;
	private String countryName;
	
	
}
