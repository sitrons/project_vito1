package org.mall.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/security/access")
@Log4j
public class AccessController {
	
	
	@GetMapping("/all")
	public void doAll() {
		log.info("allow all accounts to access..........");
	}
	
	
	@GetMapping("/admin")
	public void doAdmin() {
		log.info("allow ADMIN ONLY to access..........");
	}
	
	
	@GetMapping("/member")
	public void doMember() {
		log.info("allow MEMBERS ONLY to access..........");
	}
	
	
	//--------------------------------------------------------------------------
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')")
	@GetMapping("/annoMember")
	public void doMember2() {
		log.info("annotation member login.........");
	}
	

	@Secured({"ROLE_ADMIN"})
	@GetMapping("/annoAdmin")
	public void doAdmin2() {
		log.info("annotation admin login.........");
	}
	
}//end class
