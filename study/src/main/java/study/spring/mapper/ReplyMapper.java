package study.spring.mapper;

import java.util.List;

import study.spring.vo.ReplyVO;

public interface ReplyMapper {
	
	public void insert(ReplyVO replyVO);
	
	public List<ReplyVO> selectReplyList(int num);
	
	public ReplyVO selectReply(int rnum);
	
	public void delete(int rnum);
	
	public void update(ReplyVO replyVO);
}
