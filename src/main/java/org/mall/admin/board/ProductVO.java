package org.mall.admin.board;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductVO {

	Integer pno;   
	String productName, description, userid, category;
	int fileNum, sellerNo, unitPrice, stockAmount, amountSold;
	Timestamp regDate, updateDate;	
	
	
	private int replyCnt;  
		
	private List<ProductAttachVO> uploadFiles;    
	
	
	
	public void addAttachVO(ProductAttachVO vo) {
		if(uploadFiles == null) {
			uploadFiles = new ArrayList<>();
		}
		uploadFiles.add(vo);
	}
	
	
	
	
	
} //end class
