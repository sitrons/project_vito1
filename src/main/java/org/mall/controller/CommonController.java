package org.mall.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
//@RequestMapping("/security")
@Log4j
public class CommonController {

	@GetMapping("/security/accessError")
	public void accessDenied(Authentication auth, Model model) {
		log.info("access denied"+model);
		
		model.addAttribute("msg", "access denied.........");
	}
	
	
	@GetMapping({"/customLogin"})
	public void loginInput(String error, String logout, Model model) {
		
		log.info("error: "+error);
		log.info("logout: "+logout);
		
		if(error != null) {
			model.addAttribute("error", "login ERROR. check your account");
		}
		
		if(logout != null) {
			model.addAttribute("logout", "logout!");
		}

	}
	

	
	
	
	@GetMapping("/security/customLogout")
	public void logoutGET() {
		
		log.info("logout.......");
	}
	
	
}//end controller
