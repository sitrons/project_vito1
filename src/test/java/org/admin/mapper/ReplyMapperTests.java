package org.admin.mapper;

import java.util.List;
import java.util.stream.IntStream;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mall.admin.board.Criteria;
import org.mall.admin.board.ReplyVO;
import org.mall.admin.config.AdminConfig;
import org.mall.admin.mapper.ReplyMapper;
import org.mall.common.config.CommonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CommonConfig.class, AdminConfig.class})
@Log4j
public class ReplyMapperTests {

	//게시물 번호(pk, fk 관계)
	private Integer[] pnoArr = {22, 24, 25};
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	
	@Test
	public void testInsert(){
		IntStream.rangeClosed(1, 10).forEach(i -> {
			ReplyVO vo = new ReplyVO();
			
			//article no.
			vo.setPno(pnoArr[i % 5]);
			vo.setReply("댓글 테스트" + i);
			vo.setReplyer("replyer" + i);
			
			mapper.insertOne(vo);
		});
	}
	
	
	@Test
	public void testMapper(){
		log.info(mapper);
	}
	
		
	@Test
	public void testSelect() {
		Integer targetRno = 8;		
		ReplyVO vo = mapper.selectOne(targetRno);
		
		log.info(vo);
	}


	@Test
	public void testDelete() {
		Integer targetRno = 10;		
		mapper.deleteOne(targetRno);
	}
	
	@Test
	public void updateOne() {
		Integer targetRno = 1;		
		
		ReplyVO vo = mapper.selectOne(targetRno);
		vo.setReply("update reply");
		
		int count = mapper.updateOne(vo);
		log.info("update count: "+ count);
	}
	
	
	@Test
	public void testList() {
		Criteria cri = new Criteria();
		
		List<ReplyVO> replies = mapper.getListWithPaging(cri, pnoArr[0]);
		replies.forEach(reply -> log.info(reply));
	}
	
	
	
	@Test
	public void testList2() {
		Criteria cri = new Criteria(1, 5);
		List<ReplyVO> replies = mapper.getListWithPaging(cri, 25);
		replies.forEach(reply -> log.info(reply));
	}
	
} //end class
