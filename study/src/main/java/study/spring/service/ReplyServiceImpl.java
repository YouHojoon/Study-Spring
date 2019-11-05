package study.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import study.spring.dao.ReplyDAO;
import study.spring.dto.RpageDTO;
import study.spring.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService{
	@Autowired
	private ReplyDAO dao;
	@Override
	public void register(ReplyVO replyVO) {
		dao.insert(replyVO);
	}
	@Override
	public ReplyVO selectReply(int rnum) {
		return dao.selectRelply(rnum);
	}
	@Override
	public List<ReplyVO> selectReplyList(RpageDTO rpageDTO) {
		return dao.selectReplyList(rpageDTO);
	}
	@Override
	public void update(ReplyVO replyVO) {
		dao.update(replyVO);
	}
	@Override
	public void delete(int rnum) {
		dao.delete(rnum);
	}
	@Override
	public int totalCount(int num) {
		return dao.totalCount(num);
	}
}
