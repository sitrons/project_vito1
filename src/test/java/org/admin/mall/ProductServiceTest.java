package org.admin.mall;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mall.admin.board.Criteria;
import org.mall.admin.board.ProductVO;
import org.mall.admin.config.AdminConfig;
import org.mall.admin.dto.ProductDTO;
import org.mall.admin.mapper.ProductMapper;
import org.mall.admin.service.ProductService;
import org.mall.common.config.CommonConfig;
import org.mall.common.dto.PageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CommonConfig.class, AdminConfig.class})
@Log4j
public class ProductServiceTest {
	
	@Autowired
	ProductMapper mapper;
	
	@Autowired
	ProductService service;
	
	
	

	@Test
	public void testTotalCount() {
//		log.info("총 상품 개수: "+ mapper.selectTotalCount(cri));		
	}
	
	
	@Test
	public void testSelectOne() {
		
		ProductDTO product = service.selectOne(45);
		log.info("상품 하나: "+ product);
	}
	
	
	@Test                  
	public void testSelectList() {
		
		service.selectList(Criteria.builder()
								  .page(2)
								  .amount(5)
								  .build())
	           .forEach(dto -> log.info(dto));
		
	}          //lambda...  forEach to iterate a List
	           //forEach(argument) -> { //body }
	
	
	
	
	
	@Test
	public void testInsertOne() {
		mapper.insertOne(ProductVO.builder()
				                .sellerNo(12345)
				                .productName("테스트111")
				                .description("설명_테스트111")
		//		                .image("productImage111.jpg", "productImage222.jpg")
				                .unitPrice(5000)
				                .stockAmount(100)
				                .amountSold(0)
				                .build());
	}
	
	
	
	
	
	

	
	@Test
	public void testDeleteOne() {
		mapper.deleteOne(39);	
	}
	
		
	
	
	
} //end class
