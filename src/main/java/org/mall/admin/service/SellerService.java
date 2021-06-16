package org.mall.admin.service;

import java.util.List;

import org.mall.admin.board.SellerVO;
import org.mall.admin.dto.SellerDTO;


public interface SellerService {

	
//		public SellerVO read(String userid) ;

	
		void insertOne(SellerDTO dto);
		
		int selectTotalCount();
		
		SellerDTO selectOne(int sellerNo);

	
		List<SellerDTO> selectList();

		void updateOne(SellerDTO dto);	


		void deleteOne(int sellerNo);
	
		
		

//___________________________________________________________________________________________
// Data transfer
//___________________________________________________________________________________________
			
			
	// DTO to VO 
	default SellerVO toVO(SellerDTO dto) {
	
		SellerVO vo = SellerVO.builder()
						      .sellerNo(dto.getSellerNo())   
						      .name(dto.getName())
						      .description(dto.getDescription())
						      .category(dto.getCategory())
						      .build();
		return vo;
	}
		
		
	// VO to DTO 
	default SellerDTO toDTO(SellerVO vo) {
			
		SellerDTO dto = new SellerDTO();
					         dto.setSellerNo(vo.getSellerNo());
					         dto.setName(vo.getName());
					         dto.setDescription(vo.getDescription());
					         dto.setCategory(vo.getCategory());
		return dto;
	}
		
				
	
} //end interface
