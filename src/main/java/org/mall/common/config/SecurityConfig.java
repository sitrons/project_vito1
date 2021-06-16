package org.mall.common.config;


import javax.sql.DataSource;

import org.mall.security.CustomLoginSuccessHandler;
import org.mall.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.filter.CharacterEncodingFilter;

import lombok.Setter;
import lombok.extern.log4j.Log4j;


@Configuration
@EnableWebSecurity
@Log4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {


	@Setter(onMethod_ = @Autowired)
	public DataSource dataSource;
	
	
	@Bean 
	public UserDetailsService customUserService() {
		return new CustomUserDetailsService();
	}
	
	@Bean  
	public AuthenticationSuccessHandler loginSuccessHandler() {
		return new CustomLoginSuccessHandler();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	
	//encode에서 여기로 필터 변경해서 사용
	public CharacterEncodingFilter encodeFilter() {
		CharacterEncodingFilter chf = new CharacterEncodingFilter();
		chf.setEncoding("UTF-8");
		chf.setForceEncoding(true);
		
		return chf;
	}
	
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserService()).passwordEncoder(passwordEncoder());
	}

		

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		//korean filter
		http.addFilterBefore(encodeFilter(), ChannelProcessingFilter.class);
		
		http.authorizeRequests().antMatchers("/security/access/all").permitAll()
								.antMatchers("/security/access/admin").access("hasRole('ROLE_ADMIN')")
								.antMatchers("/security/access/member").access("hasRole('ROLE_MEMBER')");

		
		http.formLogin().loginPage("/customLogin").loginProcessingUrl("/login").successHandler(loginSuccessHandler());
	
		http.logout().logoutUrl("/security/customLogout").invalidateHttpSession(true).deleteCookies("remember-me", "JSESSION_ID");
	
		//http.rememberMe().key("sitron").tokenRepository(persistentTokenRepository()).tokenValiditySeconds(604800);
		
		http.rememberMe().key("sitron").tokenValiditySeconds(604800);
	
		http.csrf().disable();
		
		//ACCESSDENIEDHANDLER() 사용 필요.	
				
	}
	
	
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
		return repo;
	}
	
	
}//end class
