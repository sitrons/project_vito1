package org.mall.admin.service;

import java.util.List;

import org.mall.admin.board.SellerVO;
import org.mall.admin.dto.SellerDTO;
import org.mall.member.domain.MemberVO;

public interface MemberService {

	
		public MemberVO read(String userid) ;

	
		void insertOne(MemberVO vo);
		
		int selectTotalCount();
		
		MemberVO selectOne(int userid);
		
	
		List<MemberVO> selectList();

		void updateOne(MemberVO vo);	

		

		void deleteOne(int userid);
		
		
/*

		default MemberVO toVO(SellerDTO dto) {
		
			MemberVO vo = MemberVO.builder()
						          .sno(dto.getSno())
						          .sellerNo(dto.getSellerNo())
						          .sellerID(dto.getSellerID())
						          .sellerPW(dto.getSellerPW())
						          .storeName(dto.getStoreName())
						          .category(dto.getCategory())
						          .totalProductCount(dto.getTotalProductCount())
						          .build();
			return vo;
			}
			

		default SellerDTO toDTO (MemberVO vo) {
			
			SellerDTO dto = new SellerDTO();
					          dto.setSno(vo.getSno());
					          dto.setSellerNo(vo.getSellerNo());
					          dto.setSellerID(vo.getSellerID());
					          dto.setSellerPW(vo.getSellerPW());
					          dto.setStoreName(vo.getStoreName());
					          dto.setCategory(vo.getCategory());
					          dto.setTotalProductCount(vo.getTotalProductCount());
			return dto;
			
		}		
*/
		
	
} //end interface
