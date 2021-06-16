package org.admin.dao;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mall.common.config.CommonConfig;
import org.admin.dao.DataSourceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CommonConfig.class})
@Log4j
public class DataSourceTest {

	
	
	@Autowired
	DataSource dataSource;
	
	
	@Test
	public void testConnection() throws Exception{
		Connection con = dataSource.getConnection();
		log.info("★★★jdbc connection: "+ con);
		con.close();
	}
	
	
	@Autowired
	SqlSessionFactory sqlSessionFactory;
	
	@Test
	public void testConn2() throws Exception{
		SqlSession session = sqlSessionFactory.openSession();
		log.info("★★★sql session:"+ session);
		session.close();
	}
	    
	
	
	
} // end class
