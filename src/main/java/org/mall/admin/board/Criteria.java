package org.mall.admin.board;


import org.springframework.web.util.UriComponentsBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Criteria {
	
	private int page;      
	private int amount;   
	
	private String type;
	private String keyword;
	
	
	public Criteria() {
		this(1, 8);
	}	

	public Criteria(int page, int amount) {
		this.page = page;
		this.amount = amount;
	}
	
	
	public int getPage() {
		return this.page <= 0 ? 1 : this.page;
	}
	
	
	public int getStart() {
		return this.getSkip() - this.amount < 0 ? 0 : this.getSkip() - this.amount;
	}

	public int getSkip() {		     
		return (page - 1) * amount;
	}	

	
	public String getType() {
		return type == null || type.trim().length() == 0? null: type;
	}
	

	public String[] getTypeArr() {
		return type == null? new String[] {}: type.split("");
	}
	
	

	public String getListLink() {
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
			                                               .queryParam("page", this.page)
														   .queryParam("amount", this.getAmount())
														   .queryParam("type", this.getType())
														   .queryParam("keyword", this.getKeyword());
		return builder.toUriString();
	}
	
	
	
	
	
} //end class
