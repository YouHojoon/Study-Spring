package study.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import study.spring.dao.ReplyDAO;
import study.spring.dto.RpageDTO;
import study.spring.mapper.BoardMapper;
import study.spring.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService{
	@Autowired
	private ReplyDAO dao;
	@Autowired
	private BoardMapper mapper;
	
	@Transactional
	@Override
	public void register(ReplyVO replyVO) {
		dao.insert(replyVO);
		mapper.updateReplyCnt(replyVO.getNum(), 1);
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
	@Transactional
	@Override
	public void delete(int rnum) {
		dao.delete(rnum);
		mapper.updateReplyCnt(dao.selectRelply(rnum).getNum(),-1);
	}
	@Override
	public int totalCount(int num) {
		return dao.totalCount(num);
	}
}
