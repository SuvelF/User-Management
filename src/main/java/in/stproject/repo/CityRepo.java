package in.stproject.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.stproject.entity.CityEntity;

public interface CityRepo extends JpaRepository<CityEntity, Integer>{

	public List<CityEntity> findByStateStateId(Integer stateId);
	
	
}
