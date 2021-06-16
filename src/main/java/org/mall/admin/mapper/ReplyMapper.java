package org.mall.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mall.admin.board.Criteria;
import org.mall.admin.board.ReplyVO;

public interface ReplyMapper {

	
	public int insertOne(ReplyVO vo);
	
	public ReplyVO selectOne(Integer rno);
	
	public int deleteOne(int rno);
	
	public int updateOne(ReplyVO reply);
	
	
	
	public List<ReplyVO> getListWithPaging(@Param("cri") Criteria cri, 
			                               @Param("pno") Integer pno); 

	public int getCountByPno(Integer pno);
	
} //end interface
