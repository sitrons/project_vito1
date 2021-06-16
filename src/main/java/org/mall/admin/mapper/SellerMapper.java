package org.mall.admin.mapper;

import java.util.List;

import org.mall.admin.board.SellerVO;

public interface SellerMapper {
//미사용
	
	void insertOne(SellerVO vo);
	

	int selectTotalCount();

	SellerVO selectOne(Integer sno);

	List<SellerVO> selectList();

	void updateOne(SellerVO vo);	

	
	void deleteOne(int sno);

	

	
	
} //end interface