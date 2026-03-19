package in.stproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.stproject.entity.CountryEntity;

public interface CountryRepo extends JpaRepository<CountryEntity, Integer>{

}
