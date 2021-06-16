package org.mall.admin.controller;


import org.mall.admin.board.Criteria;
import org.mall.admin.board.ReplyVO;
import org.mall.admin.service.ReplyService;
import org.mall.common.dto.ReplyPageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/replies/")
@RestController
@Log4j
@AllArgsConstructor
public class ReplyController {   	
	
	
	private ReplyService service;

	
	@PostMapping(value= "/new", consumes= "application/json", produces= { MediaType.TEXT_PLAIN_VALUE })
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> create(@RequestBody ReplyVO vo){                    

		int insertCount = service.insertOne(vo);
		log.info("Reply INSERT COUNT: "+ insertCount);
		
		return insertCount == 1? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	
	
	

	@GetMapping(value= "/pages/{pno}/{page}", produces= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ReplyPageDTO> getList(@PathVariable("page") Integer page, @PathVariable("pno") Integer pno) {
		
		Criteria cri = new Criteria(page, 5);
		//log.info("getList.reply pno: " + pno);
		//log.info("getList.reply cri: "+ cri);
		
		return new ResponseEntity<>(service.getListPage(cri, pno), HttpStatus.OK);
	}
	
	
	

	@GetMapping(value= "/{rno}", produces= { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") int rno) {
		log.info("get: " + rno);
		return new ResponseEntity<ReplyVO>(service.selectOne(rno), HttpStatus.OK);
	}
	

	
	@DeleteMapping(value= "/{rno}")
	@PreAuthorize("principal.member.userid == #userid")
	public ResponseEntity<String> remove(@RequestBody ReplyVO vo, @PathVariable("rno") int rno, String userid){              
		log.info("remove: "+ rno);
		return service.deleteOne(rno) == 1? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	
	

	@PreAuthorize("principal.member.userid == #userid")
	@RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH}, value= "/{rno}", consumes= "application/json")
	public ResponseEntity<String> modify(@RequestBody ReplyVO vo, @PathVariable("rno") Integer rno, String userid){

		vo.setRno(rno);
		
		log.info("rno: "+ rno);
		log.info("modify: "+ vo);
		
		return service.updateOne(vo) == 1 ? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	
	
	
	
	
	
	
}//end controller
