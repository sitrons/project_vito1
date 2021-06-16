package org.mall.admin.service;

import java.util.List;

import org.mall.admin.board.Criteria;
import org.mall.admin.board.ReplyVO;
import org.mall.admin.mapper.ProductMapper;
import org.mall.admin.mapper.ReplyMapper;
import org.mall.common.dto.ReplyPageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;


@Service
@AllArgsConstructor
@Log4j
public class ReplyServiceImpl implements ReplyService {

	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;

	@Setter(onMethod_ = @Autowired)
	private ProductMapper productMapper;
	
	
	
	@Transactional
	@Override
	public int insertOne(ReplyVO vo) {
		log.info("register..." + vo);
		
		productMapper.updateReplyCnt(vo.getPno(), 1);
		return mapper.insertOne(vo);
	}

	
	
	@Override
	public ReplyVO selectOne(Integer rno) {

		return mapper.selectOne(rno);
	}

	
	
	@Transactional
	@Override
	public int deleteOne(int rno) {

		ReplyVO vo = mapper.selectOne(rno);
		productMapper.updateReplyCnt(vo.getPno(), -1);
		return mapper.deleteOne(rno);
	}

	@Override
	public int updateOne(ReplyVO vo) {
	
		return mapper.updateOne(vo);
	}

	@Override
	public List<ReplyVO> getList(Criteria cri, Integer pno) {
		log.info("GET reply list..."+pno);
		return mapper.getListWithPaging(cri, pno);
		
	}

	
	@Override
	public ReplyPageDTO getListPage(Criteria cri, Integer pno) {
		return new ReplyPageDTO(mapper.getCountByPno(pno), 
								mapper.getListWithPaging(cri, pno));
	}

	
} //end class
