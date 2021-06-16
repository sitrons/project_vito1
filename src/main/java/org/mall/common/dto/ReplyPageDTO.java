package org.mall.common.dto;

import java.util.List;

import org.mall.admin.board.ReplyVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class ReplyPageDTO {

	private int replyCnt;
	private List<ReplyVO> list;
	
} //end class
