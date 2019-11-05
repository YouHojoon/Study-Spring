package study.spring.service;

import java.util.List;

import study.spring.dto.RpageDTO;
import study.spring.vo.ReplyVO;

public interface ReplyService {
	
	public void register(ReplyVO replyVO);
	
	public ReplyVO selectReply(int rnum);
	
	public void update(ReplyVO replyVO);
	
	public void delete(int rnum);
	
	public List<ReplyVO> selectReplyList(RpageDTO rpageDTO);
	
	public int totalCount(int num);
}

