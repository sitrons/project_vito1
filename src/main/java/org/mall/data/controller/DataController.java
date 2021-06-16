package org.mall.data.controller;


import java.io.IOException;

import org.mall.admin.board.Criteria;
import org.mall.common.dto.PageDTO;
import org.mall.data.service.DataService;
import org.mall.data.vo.CriteriaData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/data")
@RequiredArgsConstructor 
@Log4j
public class DataController {
	
	private final DataService dataService;

	
	@GetMapping("/encyclo")
	public void encycloGet(CriteriaData criteria, Model model) throws IOException {
		//log.info("★data.encyclo GET.......................");
		
		model.addAttribute("data", dataService.selectList(criteria));
		//log.info("이 값 확인------------" + dataService.selectList(criteria));
		model.addAttribute("pageMaker", new PageDTO(criteria, dataService.selectTotalCount(criteria)));

	}
	
	
	
} //end controller
