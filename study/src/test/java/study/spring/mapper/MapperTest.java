package study.spring.mapper;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import study.spring.dao.BoardDAO;
import study.spring.service.BoardService;
import study.spring.vo.BoardVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MapperTest {
	
	@Autowired
	BoardDAO dao;
	@Autowired
	BoardService service;
	@Test
	public void selectBoard() {
		BoardVO test=new BoardVO();
		test.setNum(1);
		test.setTitle("수정테스트");
		test.setWriter("수정테스트");
		test.setContent("수정테스트");
		service.update(test);
		service.delete(6);
	}
}
