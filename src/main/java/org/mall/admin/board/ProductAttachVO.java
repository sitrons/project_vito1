package org.mall.admin.board;


import lombok.Data;

@Data
public class ProductAttachVO {

	private String uuid;
	private String uploadPath;
	private String fileName;
	private String fileType;
	
	private Integer pno;
	
} //end class


