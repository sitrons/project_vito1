package org.mall.admin.task;


import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.mall.admin.board.ProductAttachVO;
import org.mall.admin.mapper.ProductAttachMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.Setter;
import lombok.extern.log4j.Log4j;


@Component
@Log4j
public class FileCheckTask {

	@Setter(onMethod_ = {@Autowired})
	private ProductAttachMapper attachMapper;
	
	
	
	private String getFolderYesterday() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		
		String str = sdf.format(cal.getTime());
		
		return str.replace("-", File.separator);
	}
	
	
	@Scheduled(cron = "0 0 2 * * *")  
	public void checkFiles() throws Exception{
		
		log.warn("file check task run................");
		log.warn(new Date());
		
		//files listed in database
		java.util.List<ProductAttachVO> fileList = attachMapper.getOldFiles();
/*		
		//ready for check file in directory with database file list
		List<Path> fileListPaths = fileList.stream().map(vo -> Paths.get("c:\\upload", vo.getUploadPath(), vo.getUuid() +"_"+ vo.getFileName())).collect(Collectors.toList());
				
		//thumbnail files
		fileList.stream().filter(vo -> vo.isFileType() == true).map(vo -> Paths.get("c:\\upload", vo.getUploadPath(), "s_" + vo.getUuid() + "_" + vo.getFileName())).forEach(p -> fileListPaths.add(p));
*/	  
		

		
	}
	
	
	
	
	
} //end class
