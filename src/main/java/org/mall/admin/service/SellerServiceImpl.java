package org.mall.admin.service;


import java.util.List;
import java.util.stream.Collectors;

import org.mall.admin.dto.SellerDTO;
import org.mall.admin.mapper.SellerMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;


@Service   
@RequiredArgsConstructor
@Log4j
public class SellerServiceImpl implements SellerService{
	
	private final SellerMapper sellerMapper;

	@Override
	public void insertOne(SellerDTO dto) {
		sellerMapper.insertOne(toVO(dto));     
	}

	@Override
	public int selectTotalCount() {
		int count = sellerMapper.selectTotalCount();
		log.info(count);
		return count;
	}

	@Override
	public SellerDTO selectOne(int sellerNo) {
	
		SellerDTO dto = toDTO(sellerMapper.selectOne(sellerNo));
		
		return dto;
	}

	@Override
	public List<SellerDTO> selectList() {
		log.info("RUN seller.list.....");
		
	    List<SellerDTO> listDTO = sellerMapper.selectList()
	    		.stream().map(vo -> toDTO(vo)).collect(Collectors.toList());
	
		return listDTO;
	}

	
	@Override
	public void updateOne(SellerDTO dto) {
		sellerMapper.updateOne(toVO(dto));		
	}
	

	@Override
	public void deleteOne(int sellerNo) {
		sellerMapper.deleteOne(sellerNo);
		
	}

	
	
	
} //end class
