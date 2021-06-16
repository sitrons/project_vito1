package org.mall.member.domain;

import java.sql.Timestamp;
import java.util.List;

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
public class MemberVO {


	String userid, userpw, username, charName, address;
	Timestamp regdate, updatedate;
	private boolean enabled;
	
	private List<AuthVO> authList;
	
	
	
	
} //end class
