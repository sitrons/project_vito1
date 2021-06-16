package org.mall.admin.service;


import java.util.List;

import org.mall.member.domain.MemberVO;
import org.mall.member.mapper.MemberMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;


@Service   
@RequiredArgsConstructor
@Log4j
public class MemberServiceImpl implements MemberService{
	
	private final MemberMapper memberMapper;

	@Override
	public void insertOne(MemberVO vo) {		
		
	}

	@Override
	public int selectTotalCount() {
		int count = memberMapper.selectTotalCount();
		return count;
	}

	@Override
	public MemberVO selectOne(int userid) {
		
//		SellerDTO dto = toDTO(memberMapper.selectOne(sno));
//		log.info("RUN member.selectOne: "+dto);
	
		MemberVO vo = memberMapper.selectOne(userid);
		
		return vo;
	}

	
/*	
	@Override
	public List<SellerDTO> selectList() {
		
		log.info("RUN seller.list.....");
		
	    List<SellerDTO> listDTO = memberMapper.selectList()
	    		.stream().map(vo -> toDTO(vo)).collect(Collectors.toList());
	
		return listDTO;
	}
*/


	@Override
	public void updateOne(MemberVO vo) {
		memberMapper.updateOne(vo);
	}

	
	@Override
	public void deleteOne(int userid) {		
		memberMapper.deleteOne(userid);
	}

	@Override
	public MemberVO read(String userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MemberVO> selectList() {
		List<MemberVO> list = memberMapper.selectList();
		return list;
	}


	
	
	
	
} //end class
