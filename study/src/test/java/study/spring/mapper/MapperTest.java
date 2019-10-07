package study.spring.mapper;


import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import study.spring.dao.BoardDAO;
import study.spring.service.BoardService;
import study.spring.vo.BoardVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MapperTest {
	private static final Logger log = LoggerFactory.getLogger(MapperTest.class);
	@Autowired
	BoardDAO dao;
	@Autowired
	BoardService service;
	@Test
	public void selectBoard() {
		BoardVO test=new BoardVO();
		test.setNum(1);
		test.setTitle("�����׽�Ʈ");
		test.setWriter("�����׽�Ʈ");
		test.setContent("�����׽�Ʈ");
		service.update(test);
		service.delete(6);
	}
}
