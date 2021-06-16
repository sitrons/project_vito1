package org.data;


import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mall.admin.board.Criteria;
import org.mall.admin.config.AdminConfig;
import org.mall.common.config.CommonConfig;
import org.mall.data.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CommonConfig.class, AdminConfig.class})
public class CrawlTests {
 
	@Autowired
	DataService dataService;

	
	
	@Test
	public void dataCrawltest() throws IOException{
		
		//Criteria cri = new Criteria() ;
		
		dataService.dataCrawl(); //.get(0);

	}
	
	
	
} //end class
