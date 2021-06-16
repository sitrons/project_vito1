package org.admin.mall;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mall.admin.board.CriteriaStore;
import org.mall.admin.board.SellerVO;
import org.mall.admin.config.AdminConfig;
import org.mall.admin.dto.SellerDTO;
import org.mall.admin.mapper.SellerMapper;
import org.mall.admin.mapper.StoreMapper;
import org.mall.admin.service.MemberService;
import org.mall.common.config.CommonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CommonConfig.class, AdminConfig.class})
@Log4j
public class SellerServiceTest {

	
	@Autowired
	SellerMapper mapper;
	
	@Autowired
	MemberService service;
	
	@Autowired
	StoreMapper storeMapper;

	
	@Test   
	public void testTotalCount() {
		log.info("총 상품 개수: "+ mapper.selectTotalCount());		
	}
	
	
	@Test    
	public void testSelectOne() {
		
//		SellerDTO seller = service.selectOne(1);
//		log.info("셀러 한명: "+ seller);
	}
	
	
	@Test                  
	public void testSelectList() {
		
		service.selectList().forEach(dto  -> log.info(dto));	//출력 코드	
	}          
	
	
	
	@Test      //스토어 정보                 
	public void testSelectList1() {	

		CriteriaStore submit = new CriteriaStore();
									submit.setPage(1);
									submit.setAmount(5);
									
		
		log.info("여기-----------------------------------" + submit);
		storeMapper.selectList(submit).forEach(vo  -> log.info(vo));	
		
	}          
	
	
	
	/*
	@Test    
	public void testInsertOne() {
		mapper.insertOne(SellerVO.builder()
				                 .sellerNo(12345)
				                 .sellerID("9999")
				                 .sellerPW("9999")
				                 .storeName("test")
				                 .category("test")
				                 .build());
	}
	*/
	
	
	
	

	
	@Test  //ok
	public void testDeleteOne() {
		mapper.deleteOne(6);	
	}
	
	
	
	
	
	
}// end class
