package org.mall.admin.dto;


import java.sql.Timestamp;
import java.util.List;

import org.mall.admin.board.ProductAttachVO;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;


@Data
public class ProductDTO {
	
	Integer pno;
	String productName, description, userid, category; 
	int fileNum, sellerNo, unitPrice, stockAmount, amountSold;
	Timestamp regDate, updateDate;	
	
	MultipartFile[] uploadFiles;   
	
	private int replyCnt;  
	

	
	
	
	
	
	
	
	
	
	
	
	
	
} //end class
