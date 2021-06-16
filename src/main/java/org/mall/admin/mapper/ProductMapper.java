package org.mall.admin.mapper;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mall.admin.board.Criteria;
import org.mall.admin.board.ProductVO;
import org.mall.admin.dto.ProductDTO;


public interface ProductMapper {

	

		void insertOne(ProductVO productVO);  
		

		public Integer insertSelectKey (ProductVO vo);
	
	
		public void insertFileNum (@Param("pno") Integer pno,
								   @Param("fileNum") int fileNum);
		
		

		int selectTotalCount(Criteria cri);
		
		ProductVO selectOne(Integer pno);

		List<ProductVO> selectList(Criteria cri);

		Collection<ProductDTO> getListWithPaging(Criteria cri);

	

		int updateOne(ProductVO vo); 
		
		
		
		public void updateReplyCnt(@Param("pno") Integer pno,
				                   @Param("amount") int amount);
		

		int deleteOne(int pno);

	
	
	
	
} // end interface
