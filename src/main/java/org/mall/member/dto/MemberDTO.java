package org.mall.member.dto;

import java.sql.Timestamp;
import java.util.List;

import org.mall.member.domain.AuthVO;

import lombok.Data;

@Data
public class MemberDTO {

	String userid, userpw, username, charName, address;
	Timestamp regdate, updatedate;
	private boolean enabled;
	
	private List<AuthVO> authList;
	
	
} //end class
