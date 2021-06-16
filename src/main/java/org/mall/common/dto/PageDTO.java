package org.mall.common.dto;

import org.mall.admin.board.Criteria;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {
	
	
	private int startPage;
	private int endPage;
	private boolean prev, next;
	
	private int total;
	private Criteria cri;

	
	public PageDTO(Criteria cri, int total) {
		
		this.cri = cri;
		this.total = total;

		this.endPage = (int) (Math.ceil(cri.getPage() / 8.0))* 8;           
		this.startPage = this.endPage - 7; 

		int realEnd = (int) (Math.ceil((total * 1.0) / cri.getAmount()));

		if (realEnd < this.endPage) {
			this.endPage = realEnd;
			}
	
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
	}
	
	

	
} //end class
