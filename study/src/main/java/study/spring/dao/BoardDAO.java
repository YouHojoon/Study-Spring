package study.spring.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import study.spring.dto.BoardPageDTO;
import study.spring.vo.BoardVO;

@Repository
public class BoardDAO {
	@Autowired
	private SqlSession sqlsession;
	private final static String NameSpace="study.spring.mapper.BoardMapper";
	public List<BoardVO> selectBoardList(int page){
		page=(page-1)*10;
		return sqlsession.selectList(NameSpace+".selectBoardList",page);
	}
	public void insert(BoardVO boardVO) {
		sqlsession.insert(NameSpace+".insert",boardVO);
	}
	public BoardVO selectBoard(int num) {
		return sqlsession.selectOne(NameSpace+".selectBoard", num);
	}
	public void delete(int num) {
		sqlsession.delete(NameSpace+".delete", num);
	}
	public void update(BoardVO boardVO) {
		sqlsession.update(NameSpace+".update",boardVO);
	}
	public int totalCount(BoardPageDTO boardPageDTO) {
		return sqlsession.selectOne(NameSpace+".totalCount",boardPageDTO);
	}
	public List<BoardVO> selectBoardListSerch(BoardPageDTO boardPageDTO){
		return sqlsession.selectList(NameSpace+".selectBoardListSerch",boardPageDTO);
	}
}
