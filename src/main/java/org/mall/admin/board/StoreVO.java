package org.mall.admin.board;

import java.sql.Date;

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
public class StoreVO {

	Integer sno, sellerNo;
	String storeName, storeDescription, category, address, imgURL;
	Double lat, lng;
	Date regDate, updateDate;
	
	
	
	
} //end class
