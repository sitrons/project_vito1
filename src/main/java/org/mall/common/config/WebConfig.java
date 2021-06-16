package org.mall.common.config;


import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.Registration;
import javax.servlet.ServletRegistration;

import org.mall.admin.config.AdminConfig;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {       
		return new Class[] {
							CommonConfig.class,
							AdminConfig.class,
							SecurityConfig.class,
							};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { ServletConfig.class } ;    
	}

	
	@Override
	protected String[] getServletMappings() {	
		return new String[] {"/"};     
	}

	

	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		
		registration.setInitParameter("throwExceptionIfNoHandlerFound","true");
		
		MultipartConfigElement mutipartConfig = new MultipartConfigElement("S:\\mall", 20971520, 41943040, 20971520);
		registration.setMultipartConfig(mutipartConfig);
	}
	
	
	
//요기 필터 미사용하고, security config.java 에 추가함
	protected Filter[] getservletFilters() {
		
		
		System.out.println("---------------getservletFilters-----------------------------");
		System.out.println("---------------getservletFilters-----------------------------");
		System.out.println("---------------getservletFilters-----------------------------");
		System.out.println("---------------getservletFilters-----------------------------");
		
		CharacterEncodingFilter chf = new CharacterEncodingFilter();
		chf.setEncoding("UTF-8");
		chf.setForceEncoding(true);
		
		return new Filter[] {chf};
	}
	
	
	

	
} //end class
