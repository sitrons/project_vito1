package org.mall.member.mapper;

import java.util.List;


import org.mall.member.domain.MemberVO;

public interface MemberMapper {
	
	

	public MemberVO read(String userid) ;
	
	void insertOne(MemberVO vo);
	
	int selectTotalCount();

	MemberVO selectOne(int userid);

	List<MemberVO> selectList();

	void updateOne(MemberVO vo);	

	void deleteOne(int userid);
	

	
}
