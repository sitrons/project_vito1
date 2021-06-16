package org.mall.admin.service;


import java.util.List;

import org.mall.admin.board.Criteria;
import org.mall.admin.board.ProductAttachVO;
import org.mall.admin.board.ProductVO;
import org.mall.admin.dto.ProductDTO;
import org.springframework.web.multipart.MultipartFile;



public interface ProductService {


//----------------------
// CREATE (INSERT)
//----------------------	
	
    //상품 1개 추가  register
   	int insertOne(ProductDTO productDTO);  
    	
    		
//----------------------
// READ (SELECT)
//----------------------
		
	//상품 총 갯수
   	int selectTotalCount(Criteria cri);
		
	//상품 1개 선택
	ProductDTO selectOne(int pno);    
		
	//상품 전체 리스트          
	List<ProductDTO> selectList(Criteria cri);
	
	//첨부파일 조회
	public List<ProductAttachVO> getAttachList(Integer pno);
		
		
	
	
//----------------------
// UPDATE
//----------------------	
	Integer updateOne(ProductDTO dto);		
	
	
//----------------------
// DELETE
//----------------------	
		
	//상품 1개 삭제 (p195 boolean 값 확인)
	boolean deleteOne(int pno);   
	
		
		
		
		

//___________________________________________________________________________________________
// Data transfer
//___________________________________________________________________________________________
	

	
	// DTO to VO 
	default ProductVO toVO(ProductDTO dto) {
	
		ProductVO vo = ProductVO.builder()
						       .pno(dto.getPno())   
						       .productName(dto.getProductName())
						       .description(dto.getDescription())
						       .fileNum(dto.getFileNum())
						       //.uploadFiles(dto.getUploadFiles())
						       .userid(dto.getUserid())
						       .unitPrice(dto.getUnitPrice())
						       .stockAmount(dto.getStockAmount())
						       .sellerNo(dto.getSellerNo())
						       .regDate(dto.getRegDate())
						       .updateDate(dto.getUpdateDate())
						       .category(dto.getCategory())
						       .build();
		return vo;
	}
		
		
	// VO to DTO 
	default ProductDTO toDTO(ProductVO vo) {
			
		ProductDTO dto = new ProductDTO();
					         dto.setPno(vo.getPno());
							 dto.setProductName(vo.getProductName());
							 dto.setDescription(vo.getDescription());
							 dto.setCategory(vo.getCategory());
							 dto.setFileNum(vo.getFileNum());
							 //dto.setUploadFiles(vo.getUploadFiles());		
							 dto.setUserid(vo.getUserid());
							 dto.setUnitPrice(vo.getUnitPrice());
							 dto.setStockAmount(vo.getStockAmount());
							 dto.setAmountSold(vo.getAmountSold());		
							 dto.setSellerNo(vo.getSellerNo());
							 dto.setRegDate(vo.getRegDate());
							 dto.setUpdateDate(vo.getUpdateDate());
		return dto;
	}
		
		
		
		
		
	
} //end interface
