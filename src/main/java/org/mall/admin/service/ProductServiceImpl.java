package org.mall.admin.service;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.mall.admin.board.Criteria;
import org.mall.admin.board.ProductAttachVO;
import org.mall.admin.board.ProductVO;
import org.mall.admin.dto.ProductDTO;
import org.mall.admin.mapper.ProductAttachMapper;
import org.mall.admin.mapper.ProductMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Service
@Log4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

	
	private final ProductMapper productMapper;	
	private final ProductAttachMapper attachMapper;
	
	
	
	@Transactional  
	@Override
	public int insertOne(ProductDTO productDTO) {
		
		int fileLength = productDTO.getUploadFiles().length;
		
		if (productDTO.getUploadFiles() == null || fileLength <= 0) {
			//log.info("사진 미등록. (alert창 띄우기)");
			return (Integer) null;
		}

				
		String uploadFolder = "S:\\mall\\";
		String uploadFolderPath = getFolder();    
		File uploadPath = new File(uploadFolder, uploadFolderPath);


	
		// 1. vo로 변환
		ProductVO vo = toVO(productDTO);
		
		
		log.info("--------------------------------------");
		log.info("--------------------------------------");
		log.info(productDTO);
		log.info("--------------------------------------");
		log.info("--------------------------------------");
		
		
		
		// 2. db등록 (텍스트)
		productMapper.insertSelectKey(vo);
		int pno = vo.getPno();

		productMapper.insertFileNum(pno, fileLength);			
		
	
		// 3. db등록 (이미지)
		MultipartFile[] arr = productDTO.getUploadFiles();
		
		for(MultipartFile f : arr) {
						
			ProductAttachVO attachVO = new ProductAttachVO();			
			vo.addAttachVO(attachVO);			
			

			UUID uuid = UUID.randomUUID();
			String fileName = f.getOriginalFilename();
			String saveName = uuid + "_" + fileName;	
			
					
			
			try {
				
				if (uploadPath.exists() == false) {
					uploadPath.mkdirs();               
				};
				
				log.info(uploadPath);
				log.info(saveName);
				log.info("--------------------------------");
				
				//3-1.실제 파일 -> 드라이버에 저장 ===================================================================================
				File saveFile = new File(uploadPath, saveName);						
				f.transferTo(saveFile);

				
				if (checkImageType(saveFile)) {

					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + saveName));
					Thumbnailator.createThumbnail(f.getInputStream(), thumbnail, 250, 320);
					thumbnail.close();
				}
				
				//3-2.파일명 -> db에 저장  =================================================================================
				attachVO.setUuid(uuid.toString());
				attachVO.setUploadPath(getFolder());  
				attachVO.setFileName(fileName);
				attachVO.setFileType("1");
				attachVO.setPno(pno);
	//			log.info("---------------------------");
	//			log.info("---------------------------");
	//			log.info(attachVO);
				attachMapper.insert(attachVO);	


			} catch(Exception e) {
				e.printStackTrace();
				log.error(e.getMessage());
			}
		}
		return pno;
}  //end 

	
	
	
	
	@Override
	public int selectTotalCount(Criteria cri) {
		int count = productMapper.selectTotalCount(cri);
		log.info("RUN selectTotalCount: "+count);
		return count;
	}
 
	
	@Override    
	public ProductDTO selectOne(int pno) {
		ProductVO product = productMapper.selectOne(pno);
		return toDTO(product);
	}

	
	@Override    
	public List<ProductDTO> selectList(Criteria cri) {
		
		return (List<ProductDTO>) productMapper.getListWithPaging(cri);
		
/*		return productMapper.selectList(cri)
        .stream().map(vo -> toDTO(vo)).collect(Collectors.toList());  */
	} 
	
			

	@Transactional
	@Override
	public Integer updateOne(ProductDTO dto) {
		
		
		ProductVO vo = toVO(dto);
		int pno = vo.getPno();
		
		MultipartFile[] arr = dto.getUploadFiles();
		int fileLength = arr.length;
						
		
		//0. 파일 개수 확인하기
		if (arr == null || fileLength <= 0) {
			
			//1. 파일 개수 없을 시, 상품 정보만 등록
			productMapper.updateOne(vo);

			return (Integer) null;			
		} 
		
		//1. 파일 있을시, 첨부파일 전체 삭제
		attachMapper.deleteAll(dto.getPno());
				
		//2. 텍스트 등록 (데이터 변환)
		productMapper.updateOne(vo);
		productMapper.insertFileNum(pno, fileLength);
				
		
		String uploadFolder = "S:\\mall\\";
		String uploadFolderPath = getFolder();    
		File uploadPath = new File(uploadFolder, uploadFolderPath);
		ProductAttachVO attachVO = new ProductAttachVO();
		
		
		//3. 사진 등록
		for(MultipartFile f : arr) {
			
			vo.addAttachVO(attachVO);
			
			UUID _uuid = UUID.randomUUID();
			String uuid = _uuid.toString();
			String fileName = f.getOriginalFilename();
			String saveName = uuid + "_" + fileName;
			
			
			//3-1. 실제파일 등록
			/*
			File saveFile = new File(uploadPath, saveName);						
			f.transferTo(saveFile);
			
			if (checkImageType(saveFile)) {

				FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + saveName));
				Thumbnailator.createThumbnail(f.getInputStream(), thumbnail, 250, 320);
				thumbnail.close();
			}
				*/
			
			File saveFile = new File(uploadPath, saveName);						
			try {
				f.transferTo(saveFile);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (checkImageType(saveFile)) {

				FileOutputStream thumbnail = null;
				
				try {
					thumbnail = new FileOutputStream(new File(uploadPath, "s_" + saveName));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					Thumbnailator.createThumbnail(f.getInputStream(), thumbnail, 250, 320);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					thumbnail.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			
			
			//3-2. db에 등록			
			attachVO.setUuid(uuid);
			attachVO.setUploadPath(getFolder());  //-----------------------------------------------------------------------------------------------------------
			attachVO.setFileName(fileName);
			attachVO.setFileType("1");
			attachVO.setPno(pno);
			
			log.info("-----------------------  서비스------------------------------------");
			log.info(attachVO);
			
			attachMapper.insert(attachVO); 		
		} //end for
		
				
			
		return pno;   
	}
	
	

		

	
	@Transactional
	@Override
	public boolean deleteOne(int pno) {
		attachMapper.deleteAll(pno);	  //상품 삭제전, 등록된 이미지파일 다 삭제후 상품삭제 진행
		
		return productMapper.deleteOne(pno) == 1;
	}



	@Override
	public List<ProductAttachVO> getAttachList(Integer pno) {
		
		return attachMapper.findByPno(pno);
	}

	
	
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		
		return str.replace("-", File.separator);
	}
	
	private boolean checkImageType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());
			return contentType.startsWith("image");
		} catch(IOException e) {
			e.printStackTrace();
		}
	return false;
	}
	
} //end class













