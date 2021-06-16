package org.mall.admin.service;

import java.util.List;

import org.mall.admin.board.Criteria;
import org.mall.admin.board.ReplyVO;
import org.mall.common.dto.ReplyPageDTO;

public interface ReplyService {

	
	public int insertOne(ReplyVO vo);
	
	public ReplyVO selectOne(Integer rno);    //get
	
	public int deleteOne(int rno);            //remove
	
	public int updateOne(ReplyVO vo);         //modify
	
	
	public List<ReplyVO> getList(Criteria cri, Integer pno); 
	
	public ReplyPageDTO getListPage(Criteria cri, Integer pno);
	
	
}//end interface
