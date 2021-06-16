package org.mall.admin.mapper;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mall.admin.board.Criteria;
import org.mall.admin.board.CriteriaStore;
import org.mall.admin.board.SellerVO;
import org.mall.admin.board.StoreVO;
import org.mall.admin.controller.SellerController;
import org.mall.admin.dto.ProductDTO;

import lombok.extern.log4j.Log4j;



public interface StoreMapper {
	

	void insertOne(StoreVO vo);
	
	
	int selectTotalCount(CriteriaStore cri);
	
	StoreVO selectOne(Integer sno);
	
	List<StoreVO> selectList(@Param("cri") CriteriaStore cri);   
	
	
} //end interface