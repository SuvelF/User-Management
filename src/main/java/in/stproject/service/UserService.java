package in.stproject.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import in.stproject.dto.QuoteResponseDto;
import in.stproject.dto.ResetpwdDto;
import in.stproject.dto.UserDto;



public interface UserService {

	
	
	public UserDto login(String email, String pwd);
	
	
	public Map<Integer, String> getCountries();
	
	public Map<Integer, String> getState(Integer countryId);
	
	public Map<Integer, String> getCity(Integer stateId);
	
	
	public boolean isEmailUnique(String email);
	
	public boolean register(UserDto userDto);
	
	
	public boolean resetPwd(ResetpwdDto resetPwdDto);
	
	
	public QuoteResponseDto getQuotation();
	
}
