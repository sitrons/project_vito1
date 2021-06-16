package org.mall.admin.controller;


import java.text.SimpleDateFormat;

import org.mall.admin.board.Criteria;
import org.mall.admin.service.ProductService;
import org.mall.admin.service.SellerService;
import org.mall.admin.service.MemberService;
import org.mall.common.dto.PageDTO;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;


@Controller
@RequestMapping({"/admin"}) 
@RequiredArgsConstructor 
@Log4j
public class AdminController {  //extends HttpServlet{

	private final ProductService productService;
	private final SellerService sellerService;
	
	
	
	@RequestMapping("/test")        // localhost:8080/admin/test 
	public void test() {
//		log.info("★TEST mapping............................");
	}	

	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
	}
	

	
		@GetMapping({ "/", "/main",  "/includes/header" }) 
		public void mainGet(@ModelAttribute("cri") Criteria cri, Model model) { 
//			log.info("★ADMIN.list GET.......................");

		
			model.addAttribute("productList", productService.selectList(cri));
			model.addAttribute("pageMaker", new PageDTO(cri, productService.selectTotalCount(cri)));
		    model.addAttribute("sellerList", sellerService.selectList());
		    
//		    log.info(memberService.selectList());
		}

		


	@GetMapping({ "/calorie" }) 
	public void calorieGet() { 
		log.info("★ADMIN.calorie GET.......................");

	}

	

	@GetMapping({ "/encyclo" }) 
	public String encycloGet() { 
		log.info("★ADMIN.encyclo GET.......................");
	
	return "/data/encyclo" ;
	}
			
			
	
	
	@PostMapping("/listp") 
	public String listPost(@ModelAttribute("pageDTO") PageDTO pageDTO) { 
//		log.info("★ADMIN.list POST.......................");

		return "redirect:/admin/list";
	}
	
	
	
	@GetMapping({ "/list" }) 
	public void listGet(@ModelAttribute("cri") Criteria cri, Model model) { 
//		log.info("★ADMIN.list GET.......................");

	
		model.addAttribute("productList", productService.selectList(cri));
		model.addAttribute("pageMaker", new PageDTO(cri, productService.selectTotalCount(cri)));
	    model.addAttribute("sellerList", sellerService.selectList());
	    
//	    log.info(memberService.selectList());
	}

	
	 
	 
	 
} // end class
