package org.mall.data.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.mall.admin.board.Criteria;
import org.mall.data.mapper.DataMapper;
import org.mall.data.vo.CriteriaData;
import org.mall.data.vo.DataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service   
@RequiredArgsConstructor
@Log4j
public class DataServiceImpl implements DataService {

	@Autowired
	DataMapper dataMapper;
	
	
	@Override
	public List<DataVo> dataCrawl() throws IOException {		

		DataVo singleData = new DataVo();
		List<String> urlList = new ArrayList<String>() ;
		
		
		try {			
			for (int p=1; p<=3; p++) {	   
				//1. Document -> Jsoup 얻어온 결과 HTML 전체 문서
				Document doc1 = Jsoup.connect("https://terms.naver.com/list.naver?cid=51002&categoryId=51002").get();
	
				//2. Element -> Document의 HTML 요소
				Elements selectDoc1 = doc1.select(".info_area .title");  
						
				
				for (Element selectDoc1URL : selectDoc1) {				
					urlList.add(selectDoc1URL.select("a").get(0).absUrl("href")) ;			
				}//end for
			}
			
			
			//3. 각 페이지별 Document 불러오기     
			for (int i = 0; i < urlList.size(); i++) {
				Document doc2 = Jsoup.connect(urlList.get(i)).get();
		
			//4. 개별 페이지 HTML요소 가져오기			
			String name = doc2.select(".headword").html();    
			StringBuffer contentData = new StringBuffer();			
			StringBuffer contentURL = new StringBuffer();
			
			
				for (int j=0; j < name.length(); j++) {     //name.length()  							
					Elements titles = doc2.select(".stress");
					Elements data = doc2.select(".txt");
					 					
					
					for(int z= 0; z < titles.size(); z++) {								
						String t = titles.get(z).html();
						String d = data.get(z).html().replaceAll("\\<.*?>", "");						
												
						contentData.append((z+1) + ".[" +t+ "] " + d + " ");								
					}
							
				}//end for
				

				Elements images = doc2.select(".relatedImageLink img ");
				
				List<String> imageStrList = images.stream().map(img -> img.attr("data-src")).collect(Collectors.toList());
				
		
				singleData = DataVo.builder()
								   .name(name)
								   //.title(contentTitle.toString())
							  	   .content(contentData.toString())
								   .imgUrl(imageStrList.toString())
								   .build();						
					
				//5. db에 넣기
				dataMapper.insertOne(singleData);
				
			}//end for (urlList)	
			
					
		} catch (IOException e) {
			e.printStackTrace();			
		}
		
		return null;
	}
	
	
	



	@Override
	public List<DataVo> selectList(CriteriaData cri) {

		return  dataMapper.selectList(cri);
	}






	@Override
	public int selectTotalCount(CriteriaData criteria) {
		int result = dataMapper.selectTotalCount(criteria);
		return result;
	}
	
	
	
	

} //end class











