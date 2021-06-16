package org.member;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mall.common.config.CommonConfig;
import org.mall.member.domain.MemberVO;
import org.mall.member.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CommonConfig.class})
@Log4j
public class MemberMapperTests {

	@Setter(onMethod_ = @Autowired)
	private MemberMapper mapper;

	
	@Test
	public void testRead() {
		
		MemberVO vo = mapper.read("admin90");
		log.info(vo);
		vo.getAuthList().forEach(authVO -> log.info(vo));
	}
	
	
}
