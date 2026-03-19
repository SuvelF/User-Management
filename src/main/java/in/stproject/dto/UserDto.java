package in.stproject.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {

	
	private Integer userId;
	private String name;
	private String email;
	private String pwd;
	private String pwdUpdaed;
	private long phno;
	
	
	private Integer countryId;
	private Integer stateId;
	private Integer cityId;
	
	
}
