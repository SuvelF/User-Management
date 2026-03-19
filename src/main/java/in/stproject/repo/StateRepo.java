package in.stproject.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.stproject.entity.StateEntity;

public interface StateRepo extends JpaRepository<StateEntity, Integer>{

	
	public List<StateEntity> findByCountryCountryId(Integer countryId);
	
}
