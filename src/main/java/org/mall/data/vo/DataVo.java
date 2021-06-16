package org.mall.data.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DataVo {

	Integer dno;
	int date;
	String  month, category, name, title, content, resource, imgUrl;
	Date regDate;
	
	
} //end class
