package study.spring.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import study.spring.dto.RpageDTO;
import study.spring.vo.ReplyVO;
@Repository
public class ReplyDAO {
	@Autowired
	private SqlSession sqlsession;
	private static final String NameSpace="study.spring.mapper.ReplyMapper";
	
	public List<ReplyVO> selectReplyList(RpageDTO rpageDTO){
		return sqlsession.selectList(NameSpace+".selectReplyList",rpageDTO);
	}
	public void insert(ReplyVO replyVO) {
		sqlsession.insert(NameSpace+".insert",replyVO);
	}
	public ReplyVO selectRelply(int rnum) {
		return sqlsession.selectOne(NameSpace+".selectReply",rnum);
	}
	public void delete(int rnum) {
		sqlsession.delete(NameSpace+".delete", rnum);
	}
	public void update(ReplyVO replyVO) {
		sqlsession.update(NameSpace+".update",replyVO);
	}
	public int totalCount(int num) {
		return sqlsession.selectOne(NameSpace+".totalCount",num);
	}
}
