package in.stproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
	
	
}
