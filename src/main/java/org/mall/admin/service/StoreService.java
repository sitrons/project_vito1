package org.mall.admin.service;

import java.util.List;

import org.mall.admin.board.Criteria;
import org.mall.admin.board.CriteriaStore;
import org.mall.admin.board.StoreVO;



public interface StoreService {

		
		void insertOne(StoreVO vo);
		
		int selectTotalCount(CriteriaStore cri);
		
		StoreVO selectOne(int sno);

	
		List<StoreVO> selectList(CriteriaStore cri);

//		void updateOne(StoreVO vo);	


//		void deleteOne(int sesnollerNo);
	
		

	
	
} //end interface
