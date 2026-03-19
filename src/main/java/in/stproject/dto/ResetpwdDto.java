package in.stproject.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResetpwdDto {

	
	
	private String email;
	private String oldPwd;
	private String newPwd;
	private String confirmPwd;
	
}
