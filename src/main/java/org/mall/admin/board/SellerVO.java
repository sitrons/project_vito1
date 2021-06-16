package org.mall.admin.board;

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
public class SellerVO {

	Integer sellerNo;
	String name, description, category;
	
	
	
	
} //end class
