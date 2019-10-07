package study.spring.mapper;

import java.util.List;

import study.spring.dto.BoardPageDTO;
import study.spring.vo.BoardVO;

public interface BoardMapper {
	
	public List<BoardVO> selectBoardList(int page);
	
	public void insert();
	
	public BoardVO selectBoard(int num);
	
	public int delete(int num);
	
	public int update(BoardVO board);
	
	public int totalCount(BoardPageDTO boardPageDTO);
	
	public List<BoardVO> selectBoardListSerch(BoardPageDTO boardPageDTO);
}
