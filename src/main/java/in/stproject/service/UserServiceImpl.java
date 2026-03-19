package in.stproject.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import in.stproject.dto.QuoteResponseDto;
import in.stproject.dto.ResetpwdDto;
import in.stproject.dto.UserDto;
import in.stproject.entity.CityEntity;
import in.stproject.entity.CountryEntity;
import in.stproject.entity.StateEntity;
import in.stproject.entity.UserEntity;
import in.stproject.repo.CityRepo;
import in.stproject.repo.CountryRepo;
import in.stproject.repo.StateRepo;
import in.stproject.repo.UserRepo;



@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private CountryRepo countryRepo;
	
	@Autowired
	private StateRepo stateRepo;
	
	@Autowired
	private CityRepo cityRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private EmailService emailService;
	
	
	
	@Override
	public UserDto login(String email, String pwd) {
		
		UserEntity entity = userRepo.findByEmailAndPwd(email, pwd);
		
		if(entity!=null) {
			UserDto dto = new UserDto();
			BeanUtils.copyProperties(entity, dto);
			return dto;
		}
		
		return null;
	}

	@Override
	public Map<Integer, String> getCountries() {
		
		List<CountryEntity> list = countryRepo.findAll();
		
		Map<Integer, String> countryMap = new HashMap<>();
		
		list.forEach(country ->{
			countryMap.put(country.getCountryId(), country.getCountryName());
		});
		
		return countryMap;
	}

	@Override
	public Map<Integer, String> getState(Integer countryId) {
		  List<StateEntity> list = stateRepo.findByCountryCountryId(countryId);
		
		  Map<Integer, String> statesMap = new HashMap<>();
		  
		  list.forEach(state ->{
			  statesMap.put(state.getStateId(), state.getStateName());
		  });
				  
		  
		  return statesMap;
	}

	@Override
	public Map<Integer, String> getCity(Integer stateId) {
		
		List<CityEntity> list = cityRepo.findByStateStateId(stateId);
		
		Map<Integer, String> cityMap = new HashMap<>();
		
		list.forEach(city ->{
			cityMap.put(city.getCityId(), city.getCityName());
		});
		
		   
		return cityMap;
	}

	@Override
	public boolean isEmailUnique(String email) {
		
		UserEntity byEmail = userRepo.findByEmail(email);
		
//		return null == userRepo.findByEmail(email);
		
		return byEmail==null;
	}

	@Override
	public boolean register(UserDto userDto) {
		
		
		
		
		String randomPwd = getRandomPwd();
		userDto.setPwd(randomPwd);
		userDto.setPwdUpdaed("NO");
		
		
		UserEntity entity = new UserEntity();
		BeanUtils.copyProperties(userDto, entity);
		
		
		CountryEntity country = countryRepo.findById(userDto.getCountryId()).get();
		
		entity.setCountry(country);
		
		 StateEntity state = stateRepo.findById(userDto.getStateId()).get();
		
		 entity.setState(state);
		
		 
		CityEntity city = cityRepo.findById(userDto.getCityId()).get();
		 
		entity.setCity(city);
		
		userRepo.save(entity);
		
		
		UserEntity saveUser = userRepo.save(entity);
		
		if(saveUser!=null) {
			String subject = "Your Registration Success";
			String body = "Your Account Pwd : "+randomPwd;
			return emailService.sendEmail(userDto.getEmail(), subject, body);
		}
		
		
		return false;
	}

	@Override
	public boolean resetPwd(ResetpwdDto resetPwdDto) {
		
		UserEntity entity = userRepo.findByEmail(resetPwdDto.getEmail());
		
		entity.setPwd(resetPwdDto.getNewPwd());
		entity.setPwdUpdaed("YES");
		
		UserEntity save = userRepo.save(entity);
		
		
		
		return save != null;
	}

	@Override
	public QuoteResponseDto getQuotation() {
		String apiUrl = "https://dummyjson.com/quotes/random";
		
		RestTemplate rt = new RestTemplate();
	ResponseEntity<QuoteResponseDto> forEntity = rt.getForEntity(apiUrl, QuoteResponseDto.class);
		
		
		return forEntity.getBody();
	}
	
	
	
	private String getRandomPwd() {
		
		 String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	        StringBuilder salt = new StringBuilder();
	        Random rnd = new Random();
	        while (salt.length() < 5) { // length of the random string.
	            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
	            salt.append(SALTCHARS.charAt(index));
	        }
	        String saltStr = salt.toString();
	        return saltStr;
		
	}

}
