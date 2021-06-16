package org.mall.admin.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.mall.admin.board.Criteria;
import org.mall.admin.board.CriteriaStore;
import org.mall.admin.board.ProductAttachVO;
import org.mall.admin.board.ProductVO;
import org.mall.admin.dto.ProductDTO;
import org.mall.admin.service.MemberService;
import org.mall.admin.service.ProductService;
import org.mall.admin.service.SellerService;
import org.mall.admin.service.StoreService;
import org.mall.common.dto.PageDTO;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping({"/product"}) 
@RequiredArgsConstructor 
@Log4j
public class ProductController {
	
	private final ProductService productService;
	private final SellerService sellerService;

	
	
	@RequestMapping("/test")        
	public void test() {
//		log.info("★TEST mapping............................");
	}	

	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	
		
	
	
	@PostMapping("/listp") 
	public String listPost(@ModelAttribute("pageDTO") PageDTO pageDTO) { 
//		log.info("★ADMIN.list POST.......................");

		return "redirect:/admin/list";
	}
	
	
	
	@GetMapping({ "/", "/list",  "/includes/header" }) 
	public void listGet(@ModelAttribute("cri") Criteria cri, Model model) { 
		log.info("★ADMIN.list GET.......................");

	
		model.addAttribute("productList", productService.selectList(cri));
		model.addAttribute("pageMaker", new PageDTO(cri, productService.selectTotalCount(cri)));
	    model.addAttribute("sellerList", sellerService.selectList());
	    
//	    log.info(memberService.selectList());
	}


	
	
	
	
	
	
	
	

	
			

	@GetMapping({"/view"})
	public void viewGet(@RequestParam("pno") Integer pno, @ModelAttribute("cri") Criteria cri, Model model) {

		log.info("★PRODUCT.view GET.................");

		model.addAttribute("product", productService.selectOne(pno));			
	}


	
	@GetMapping(value="/getAttachList")    //, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<ProductAttachVO>> getAttachList(Integer pno){

		return new ResponseEntity<>(productService.getAttachList(pno), HttpStatus.OK);
	}


	
	

	@GetMapping("/register")
	@PreAuthorize("isAuthenticated()")
	public void registerGet() {

		log.info("★PRODUCT.register GET.................");
	}



	@PostMapping({"/register"})  
	//@PreAuthorize("isAuthenticated()")
	public String registerPost(@ModelAttribute("cri") Criteria cri, ProductDTO dto, RedirectAttributes rttr, Integer pno) {

	
		int resultPno = productService.insertOne(dto);
		rttr.addFlashAttribute("result", resultPno);
		
		log.info("컨트롤러 등록.---------------------------"+ resultPno); 
		
		return "redirect: /product/list";
	}



	
	
	
	@GetMapping({"/modify"})
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')")
	public void modifyGet(@RequestParam("pno") Integer pno, @ModelAttribute("cri") Criteria cri, Model model) {

		log.info("★컨트롤러.modify GET.................");
		
		model.addAttribute("product", productService.selectOne(pno));	
	
		//Timestamp time = productService.selectOne(pno).getUpdateDate();	
		//log.info("여기 시간 확인----------------------"+ time);	
	
	}


	


	@PostMapping({"/modify"})                                                                       
	//@PreAuthorize("principal.member.userid == #userid")  
	public String modifyPost(@ModelAttribute("cri") Criteria cri, ProductDTO dto, Integer pno, String userid, RedirectAttributes rttr) {

		log.info("★컨트롤러.modify POST......" + dto);

		Integer updatePno = productService.updateOne(dto);
		
		log.info("★여기 컨트롤러................................................");
		log.info(updatePno);
		
		
		if (updatePno != null) {
			
			rttr.addFlashAttribute("result", updatePno);      
		
			rttr.addAttribute("page", cri.getPage());
			rttr.addAttribute("amount", cri.getAmount());
			rttr.addAttribute("type", cri.getType());
			rttr.addAttribute("keyword", cri.getKeyword());
			
		}//end if
		
		return "redirect: /product/list" + cri.getListLink();
	}


	
	
	
	@PostMapping("/delete") 
	@PreAuthorize("principal.member.userid == #userid")
	public String deletePost(@RequestParam("pno") int pno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr, String userid) {
		
		//log.info("sellerNo:" + sellerNo);
		log.info(pno);
		log.info("★PRODUCT.delete POST.................");

		List<ProductAttachVO> attachList = productService.getAttachList(pno);
	
		if (productService.deleteOne(pno)) { 
			
			deleteFiles(attachList);  			
			rttr.addFlashAttribute("result", pno);
		}
				
		return "redirect: /product/list" + cri.getListLink();
	}


	

	
	private void deleteFiles(List<ProductAttachVO> attachList) {

		log.info("delete attach files.....");
		log.info(attachList);

		if (attachList == null || attachList.size() == 0) {
			return;
		}
		
		attachList.forEach(attach -> {
			try {
				Path file = Paths.get("F:\\mall\\"+ attach.getUploadPath() +"\\"+ attach.getUuid() +"_"+ attach.getFileName());
				Files.deleteIfExists(file);

				if (Files.probeContentType(file).startsWith("image")){
					Path thumbNail = Paths.get("F:\\mall\\"+ attach.getUploadPath() +"\\s_"+ attach.getUuid() +"_"+ attach.getFileName());
					Files.delete(thumbNail);
				}
			} catch(Exception e){
				log.error(e.getMessage());
			}
		});
	} 




} //end class