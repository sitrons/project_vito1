package org.mall.data.vo;


import org.mall.admin.board.Criteria;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@ToString
public class CriteriaData extends Criteria{
	
	public CriteriaData() {
		super(1, 10);
	}	

	
	
	
} //end class
