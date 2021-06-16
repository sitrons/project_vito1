package org.mall.security;

import org.mall.member.domain.MemberVO;
import org.mall.member.mapper.MemberMapper;
import org.mall.security.domain.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
public class CustomUserDetailsService implements UserDetailsService{
	
	@Setter(onMethod_ = @Autowired)
	private MemberMapper memberMapper;
	
	@Override
	public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {

		log.warn("load user by userid: "+userid);
		
		MemberVO vo = memberMapper.read(userid);
		return vo == null? null : new CustomUser(vo);
	}
	
}//end class
