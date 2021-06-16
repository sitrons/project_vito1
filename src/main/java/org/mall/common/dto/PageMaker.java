package org.mall.common.dto;


import org.mall.admin.board.Criteria;

import lombok.Getter;
import lombok.ToString;

@Getter 
@ToString
public class PageMaker {

	private boolean prev;
	private boolean next;
	private int start;
	private int end;
	private PageDTO pageDTO;
	private int total;
	private int lastPage;
	private Criteria cri;

	
	
	public PageMaker(Criteria cri, int total) {

		this.total = total;
		this.cri = cri;
		
		int perSheet = cri.getAmount();
		
	
		double currentPage = (double)cri.getPage();   
		int tempEnd = (int)(Math.ceil(currentPage * 0.1) * 10);

		this.start = tempEnd - 9;
		
		int realEnd = (int)(Math.ceil(total / (double)perSheet));
		
		this.end = realEnd < tempEnd ? realEnd : tempEnd;
	
		this.prev = start > 1;
		this.next = end * perSheet < total;  
		
		this.lastPage = (int)(Math.ceil(total / (double)perSheet));		
	}
	
	

}