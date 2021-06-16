package org.mall.admin.board;

import java.sql.Date;

import lombok.Data;

@Data
public class ReplyVO {

	private Integer rno;
	private Integer pno;
	
	private String reply;
	private String replyer;
	private Date replyDate;
	private Date updateDate;	
	
	
	
	
	
	
} //end class


