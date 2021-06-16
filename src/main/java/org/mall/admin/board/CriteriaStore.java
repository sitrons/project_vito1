package org.mall.admin.board;


import org.springframework.web.util.UriComponentsBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Data
@ToString
public class CriteriaStore extends Criteria {
	
	public CriteriaStore() {
		super(1, 5);
	}	

} //end class
