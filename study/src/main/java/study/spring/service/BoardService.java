package study.spring.service;

import java.util.List;
import study.spring.dto.BoardPageDTO;
import study.spring.vo.BoardVO;

public interface BoardService {
	public void register(BoardVO boardVO);
	
	public BoardVO selectBoard(int num);
	
	public void update(BoardVO boardVO);
	
	public void delete(int num);
	
	public List<BoardVO> selectBoardList(int page);
	
	public int totoalCount(BoardPageDTO boardPageDTO);
	
	public List<BoardVO> selectBoardListSerch(BoardPageDTO boardPageDTO);
}
