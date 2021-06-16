package org.mall.common.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.log4j.Log4j;

@Configuration
@MapperScan(basePackages = {"org.mall.admin.mapper", "org.mall.data.mapper", "org.mall.member.mapper"})
@ComponentScan(basePackages={"org.mall.data.service", "org.mall.admin.service", "org.mall.admin.aop", "org.mall.admin.task"})
@EnableScheduling
@EnableAspectJAutoProxy
@EnableTransactionManagement
@Log4j
public class CommonConfig  {

	
	static {
		//System.out.println("Common Config");
	}
	
	
	@Bean    
	public DataSourceTransactionManager txManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
	
	@Bean
	public String sampleRun() {
		log.info("★★★REAL CommonConfig.java 작동중");
		return "hello..... ";  
	}
	
	
	@Bean     
	public DataSource dataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
		hikariConfig.setJdbcUrl("jdbc:mysql://112.169.196.210:3310/mall?serverTimezone=Asia/Seoul");
		hikariConfig.setUsername("user1");
		hikariConfig.setPassword("user1");
//ㄴ		hikariConfig.setMaximumPoolSize(3);
		HikariDataSource ds = new HikariDataSource(hikariConfig);
		
		return ds;
	}
	
	
	@Bean        
	public SqlSessionFactory sqlSessionFactory() throws Exception{
		
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		
		return sessionFactory.getObject();
	}
	
	
	
	
	
	
} //end class
