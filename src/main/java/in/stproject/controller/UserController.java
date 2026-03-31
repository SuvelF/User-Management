package in.stproject.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import in.stproject.dto.QuoteResponseDto;
import in.stproject.dto.ResetpwdDto;
import in.stproject.dto.UserDto;
import in.stproject.service.UserService;

@Controller
public class UserController {

	
	@Autowired
	private UserService userService;
	
	
	
	@GetMapping("/")
	public  String index(Model model) {
		
		UserDto userDto = new UserDto();
		
		model.addAttribute("user",userDto);
		
		return "index";
	}
	
	
	@PostMapping("/login")
	public String login(@ModelAttribute("user") UserDto user, Model model) {
		
		UserDto login = userService.login(user.getEmail(), user.getPwd());
		if(login==null) {
			model.addAttribute("emsg","Invalid Credentials");
			return "index";
		}
		
		if(login.getPwdUpdaed().equals("YES")) {
		
			//Display Dashboard page
			
			QuoteResponseDto quotation = userService.getQuotation();
		    model.addAttribute("quote",quotation);
		    return "dashboard";
		
		}
		else {
			//Display reset pwd page
			
			ResetpwdDto resetPwd = new ResetpwdDto();
			resetPwd.setEmail(login.getEmail());
			model.addAttribute("resetPwd", resetPwd);
			return "resetPwd";
		}
		
		
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		
		UserDto userDto = new UserDto();
		
		model.addAttribute("user", userDto);
		
		Map<Integer, String> countriesMap = userService.getCountries();
		model.addAttribute("countries",countriesMap);
		
		return "register";
	}
	
	@GetMapping("/states/{countryId}")
	@ResponseBody
	public Map<Integer, String> getstates(@PathVariable Integer countryId){
		return userService.getState(countryId);
	}
	
	@GetMapping("/cities/{stateId}")
	@ResponseBody
	public Map<Integer, String> getcities(@PathVariable Integer stateId){
		return userService.getCity(stateId);
	}
	
	@PostMapping("/register")
	public String registerUser(@ModelAttribute("user") UserDto user, Model model) {
		
		boolean emailUnique = userService.isEmailUnique(user.getEmail());
		if(emailUnique) {
			
			boolean register = userService.register(user);
			
			if(register) {
				model.addAttribute("smsg", "Registration Success");
			}
			else {
				model.addAttribute("emsg", "Registration Failed");
			}
		}
			else {
			
			model.addAttribute("emsg","Duplicate Email Found");
			
		}
		
		Map<Integer, String> countriesMap = userService.getCountries();
		model.addAttribute("countries",countriesMap);
		
		return "register";
	}
	
	@PostMapping("/resetPwd")
	public String resetPwd(@ModelAttribute("resetPwd") ResetpwdDto resetPwd, Model model) {
		
		UserDto login =userService.login(resetPwd.getEmail(), resetPwd.getOldPwd());
		
		if(login == null) {
			model.addAttribute("emsg","Old Pwd is incorrect");
			return "resetPwd";
		}
		
		if(resetPwd.getNewPwd().equals(resetPwd.getConfirmPwd())){
		userService.resetPwd(resetPwd);
		QuoteResponseDto quotation = userService.getQuotation();
		model.addAttribute("quote", quotation);
		return "dashboard";
		
		}
		else {
			model.addAttribute("emsg","New Pwd and Confirm Pwd Not Matching");
			return "resetPwd";
		}
	}
	
	@GetMapping("/getQuote")
	public String getQuote(Model model) {
		QuoteResponseDto quotation = userService.getQuotation();
		model.addAttribute("quote", quotation);
		return "dashboard";
	}
	
}
