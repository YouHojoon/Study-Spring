package study.spring.mapper;

import java.util.List;

import study.spring.vo.BoardAttachFileVO;

public interface BoardAttachMapper {
	public void insert(BoardAttachFileVO boardAttachFileVO);
	
	public void delete(String uuid);
	
	public List<BoardAttachFileVO> findByNum(int num);
}
