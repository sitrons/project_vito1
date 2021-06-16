package org.mall.security;

import lombok.extern.log4j.Log4j;

@Log4j
public class CustomNoOpPasswordEncoder {

	public String encode(CharSequence rawPassword) {
		log.warn("before encode: "+rawPassword);
		return rawPassword.toString();
	}
	
	public boolean match(CharSequence rawPassword, String encodedPassword) {
		log.warn("match: "+rawPassword+ ":"+encodedPassword);
		return rawPassword.toString().equals(encodedPassword);
	}
	
	
}//end class
