package org.mall.admin.controller;


import java.util.List;

import org.mall.admin.board.CriteriaStore;
import org.mall.admin.board.StoreVO;
import org.mall.admin.service.StoreService;
import org.mall.common.dto.PageDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/store") 
@RequiredArgsConstructor 
@Log4j
public class SellerController {
	

	private final StoreService storeService;
	
	
	
	@GetMapping("/storelist")
	public void list(Model model, CriteriaStore cri){
		log.info("seller controller ......... : store list");
		
		List<StoreVO> storeList = storeService.selectList(cri);
		
		model.addAttribute("storeList", storeList);
		model.addAttribute("pageMaker", new PageDTO(cri, storeService.selectTotalCount(cri)));
		
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(storeList);
		model.addAttribute("jsonStr", jsonStr);

		
		
	}
	
	
	
	
	
	
} //end class
