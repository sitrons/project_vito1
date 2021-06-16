package org.admin.dao;

import static org.junit.Assert.assertNotNull;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mall.common.config.CommonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CommonConfig.class})
@Log4j
public class ConfigLoadTest {

	@Autowired
	private CommonConfig cc;

	@Autowired
	private DataSource ds;
	

	@Autowired
	private SqlSessionFactory sqlSessionFactory; 

	
	
		@Test   
		public void testDS() {
			log.info("★★★TEST DS...................................");
			assertNotNull(ds);
		}
		
		@Test   
		public void testSession() {
			
			SqlSession session = sqlSessionFactory.openSession();
			log.info("★★★TEST session................"+session);
			session.close();
		}
	
	
		
		@Test
		public void sampleRun() {
			log.info("★★★CommonConfig: "+ cc.sampleRun());
		}
		
		
	
} //end class
