package study.spring.mapper;

import java.util.List;

import study.spring.vo.BoardVO;

public interface BoardMapper {
	
	public List<BoardVO> selectBoardList();
	
	public void insert();
	
	public BoardVO selectBoard(int num);
	
	public int delete(int num);
	
	public int update(BoardVO board);
}
