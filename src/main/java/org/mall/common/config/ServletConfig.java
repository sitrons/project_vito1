package org.mall.common.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.multipart.MultipartResolver;

import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

                                  
@ComponentScan(basePackages = {"org.mall.controller",           
							   "org.mall.admin.controller", 
							   "org.mall.member.controller",
							   "org.mall.data.controller",
							   "org.mall.security.controller"
							  })       
@EnableGlobalMethodSecurity(prePostEnabled=true, securedEnabled=true)
@EnableWebMvc   
public class ServletConfig implements WebMvcConfigurer{
	
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		    InternalResourceViewResolver bean = new InternalResourceViewResolver();
		    bean.setViewClass(JstlView.class);
		    bean.setPrefix("/WEB-INF/views/");
		    bean.setSuffix(".jsp");
		    registry.viewResolver(bean);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}


/*	
	@Bean(name="multipartResolver")
	public CommonsMultipartResolver getResolver() throws IOException{
		
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		
		resolver.setMaxUploadSize(1024*1024*10000);  		
		resolver.setMaxUploadSizePerFile(1024*1024*2000);   
		resolver.setMaxInMemorySize(1024*1024*10000);       
		resolver.setUploadTempDir(new FileSystemResource("C:\\temp"));   		
		resolver.setDefaultEncoding("UTF-8");
		return resolver;
	}
*/	
	
	@Bean
	public MultipartResolver multipartResolver() {
		StandardServletMultipartResolver resolver = new StandardServletMultipartResolver();
		return resolver;
	}


	
} //end class           
