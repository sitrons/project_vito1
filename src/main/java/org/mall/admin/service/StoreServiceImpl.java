package org.mall.admin.service;


import java.util.List;

import org.mall.admin.board.Criteria;
import org.mall.admin.board.CriteriaStore;
import org.mall.admin.board.StoreVO;
import org.mall.admin.dto.ProductDTO;
import org.mall.admin.mapper.StoreMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;


@Service   
@RequiredArgsConstructor
@Log4j
public class StoreServiceImpl implements StoreService{
	
	private final StoreMapper storeMapper;

	@Override
	public void insertOne(StoreVO vo) {
		storeMapper.insertOne(vo);     
	}

	@Override
	public int selectTotalCount(CriteriaStore cri) {
		return storeMapper.selectTotalCount(cri);
	}

	@Override
	public StoreVO selectOne(int sno) {
		return storeMapper.selectOne(sno);
	}
		
	@Override
	public List<StoreVO> selectList(CriteriaStore cri) {      
		List<StoreVO> list = storeMapper.selectList(cri); 
		//log.info("인쇄1---------------------------------------"+list);
		//log.info("인쇄2---------------------------------------"+ list.get(0).getLat());
		
		
		
		return list;  
	}





	
	
} //end class
