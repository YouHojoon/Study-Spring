package study.spring.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.spring.dao.BoardDAO;
import study.spring.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardDAO dao;
	
	@Override
	public BoardVO selectBoard(int num) {
		return dao.selectBoard(num);
	}
	
	@Override
	public List<BoardVO> selectBoardList() {
		return dao.selectBoardList();
	}
	@Override
	public void register(BoardVO boardVO) {
		dao.insert(boardVO);
	}
	@Override
	public void update(BoardVO boardVO) {
		dao.update(boardVO);
	}
	@Override
	public void delete(int num) {
		dao.delete(num);
	}
}
