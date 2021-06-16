package org.mall.admin.mapper;

import java.util.List;
import org.mall.admin.board.ProductAttachVO;


public interface ProductAttachMapper {
	

	public void insert(ProductAttachVO vo);
	
	public void delete(String uuid);

	public List<ProductAttachVO> findByPno(Integer pno);
	
	void deleteAll(Integer pno);
	
	public List<ProductAttachVO> getOldFiles(); 
	
} //end interface
