package org.mall.data.mapper;

import java.util.List;

import org.mall.admin.board.Criteria;
import org.mall.data.vo.CriteriaData;
import org.mall.data.vo.DataVo;

public interface DataMapper {

	
	public DataVo selectOne(Integer dno);
	
	public List<DataVo> selectList(Criteria cri);

	public void insertOne(DataVo singleData);
	
	public int selectTotalCount(CriteriaData criteria);
	
	
} // end interface
