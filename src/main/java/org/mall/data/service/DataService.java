package org.mall.data.service;

import java.io.IOException;
import java.util.List;

import org.mall.admin.board.Criteria;
import org.mall.data.vo.CriteriaData;
import org.mall.data.vo.DataVo;


public interface DataService {

	
	public List<DataVo> dataCrawl() throws IOException;
	
	public List<DataVo> selectList(CriteriaData cri);

	public int selectTotalCount(CriteriaData criteria);
	
	
} //end class
