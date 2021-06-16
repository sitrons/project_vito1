package org.mall.admin.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@RequestMapping("/product") 
@Log4j
public class UploadController {

	
	
	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName) {
		//log.info("file name: " + fileName);

		File file = new File("S:\\mall\\"+ fileName);
		log.info("controller..1 file: " + file);

		
		ResponseEntity<byte[]> result = null;

		try {
			HttpHeaders header = new HttpHeaders();

			header.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
			
			log.info("controller..2 result: " + result);
		}catch (IOException e) {
			//e.printStackTrace();
		}
		
		return result;
	};



	@ResponseBody
	@GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> downloadFile(@RequestHeader("User-Agent") String userAgent, String fileName) {

		Resource resource = new FileSystemResource("S:\\mall\\" + fileName);
		log.info("resource: " + resource);

		if(resource.exists() == false) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		String resourceName = resource.getFilename();

		//remove UUID
		String resourceOriginalName = resourceName.substring(resourceName.indexOf("_") + 1);

		HttpHeaders headers = new HttpHeaders();
		try {
			String downloadName = null;
			if(userAgent.contains("Trident")) {
				downloadName = URLEncoder.encode(resourceName, "UTF-8").replaceAll("\\+", "");
				log.info("IE browser" + downloadName);
			} else if(userAgent.contains("Edge")) {
				downloadName = URLEncoder.encode(resourceName, "UTF-8");
				log.info("Endge browser" + downloadName);
			} else {
				downloadName = new String(resourceName.getBytes("UTF-8"), "ISO-8859-1");
				log.info("Chrome browser" + downloadName);
			}
			headers.add("Content-Disposition", "attachment; filename=" + downloadName);
		} catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}


	
	
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		log.info("upload ajax get................");		
	};
	
	
/*
	@ResponseBody
	@PostMapping(value = "/uploadAjaxAction")   //, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<FileDTO>> uploadAjaxPost(MultipartFile[] uploadFile) {
		log.info("upload ajax post................ 여기여기");
		
		if (uploadFile == null) {
			return null;
		}
		
		
		List<FileDTO> list = new ArrayList<>();
		
		String uploadFolder = "S:\\mall\\";
		String uploadFolderPath = getFolder();    //오늘날짜로 폴더 만들기
		File uploadPath = new File(uploadFolder, uploadFolderPath);
		
		log.info("uploadPath....."+uploadPath);

		if (uploadPath.exists() == false) {
			uploadPath.mkdirs();    //폴더 만들기
		};


		for (MultipartFile multipartFile : uploadFile) {

			FileDTO fileDTO = new FileDTO();
			String uploadFileName = multipartFile.getOriginalFilename();

			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
			//log.info("only file name: "+ uploadFileName);
			fileDTO.setFileName(uploadFileName);

			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + uploadFileName;

		
			try {
				File saveFile = new File(uploadPath, uploadFileName);
				multipartFile.transferTo(saveFile);

				fileDTO.setUuid(uuid.toString());
				fileDTO.setUploadPath(uploadFolderPath);
				log.info("uploadPath:---------------------------------------- "+ uploadPath);
				
				if (checkImageType(saveFile)) {
					
					fileDTO.setImage(true);

					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
					thumbnail.close();
				}
				list.add(fileDTO);
				
			} catch(Exception e) {
				e.printStackTrace();
				log.error(e.getMessage());
			}
		};
		return new ResponseEntity<>(list, HttpStatus.OK);
	}; 
*/

	

@PostMapping(value = "/deleteFile")
@ResponseBody	
@PreAuthorize("isAuthenticated()")
public ResponseEntity<String> deleteFile(String fileName, String type) {
	log.info("deleteFile: " + fileName);
	
	File file;
	
	try {
		file = new File("S:\\mall\\" + URLDecoder.decode(fileName, "UFT-8"));
		file.delete();
		if (type.equals("image")) {
			String largeFileName = file.getAbsolutePath().replace("s_", "");
			file = new File(largeFileName);
			file.delete();
		}
	} catch(UnsupportedEncodingException e){
		e.printStackTrace();
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
return new ResponseEntity<String>("deleted", HttpStatus.OK);
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
	
	
	

	@GetMapping("/uploadForm")
	public void uploadForm() {
		log.info("upload form................");
	}
	
	
	@PostMapping("/uploadFormAction")
	public void uploadFormPost(MultipartFile[] uploadFile, Model model) {
		
		String uploadFolder = "S:\\mall";
		
		for (MultipartFile multipartFile : uploadFile) {
			log.info("---------------------------------------");
			log.info("upload file name: " + multipartFile.getOriginalFilename());
			log.info("upload file size: " + multipartFile.getSize());
		
		File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			
			try {
				multipartFile.transferTo(saveFile);
			} catch(Exception e) {
				log.error(e.getMessage());
			} //end catch
		} //end for
	}
	
	
	
	
} //end class
