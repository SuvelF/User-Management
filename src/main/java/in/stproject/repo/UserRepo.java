package in.stproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.stproject.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Integer>{

	
	public UserEntity findByEmailAndPwd(String email,String pwd);
	
	public UserEntity findByEmail(String email);
	
}
