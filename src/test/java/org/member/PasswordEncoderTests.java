package org.member;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mall.common.config.CommonConfig;
import org.mall.common.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CommonConfig.class, SecurityConfig.class})
@Log4j
public class PasswordEncoderTests {

	@Setter(onMethod_ = @Autowired)
	private PasswordEncoder pwencoder;
	
	@Test
	public void testEncode() {
		String str = "store7";
		
		String enStr = pwencoder.encode(str);
		
		log.info(enStr);
	}
	
	
}
