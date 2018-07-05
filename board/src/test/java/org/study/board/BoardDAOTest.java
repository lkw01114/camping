package org.study.board;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.study.board.model.BoardVO;
import org.study.board.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class BoardDAOTest {
	
	@Autowired
	BoardDAO dao;
	
	private static Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);
	
	@Test
	public void testCreate() throws Exception {
		BoardVO vo = new BoardVO();
		vo.setTitle("제목입니다.");
		vo.setContent("내용입니다.");
		vo.setWriter("user00");
		dao.create(vo);
	}
	
	@Test
	public void testRead() throws Exception{
		BoardVO vo = dao.read(4);
		System.out.println("4번>>>>  " + vo);
	}
	
	@Test
	public void testUpdate() throws Exception{
		BoardVO vo = new BoardVO();
		vo.setTitle("제목입니다.111111111");
		vo.setContent("내용입니다.111111111");
		vo.setBno(4);
		dao.update(vo);
	}
	
	@Test
	public void testDelete() throws Exception{
		dao.delete(2);
	}
	
	@Test
	public void testSelectAll() throws Exception{
		List<BoardVO> list = dao.listAll();
		System.out.println(list);
	}
	
	@Test
	public void testListPage() throws Exception{
		int page = 10;
		List<BoardVO> list = dao.listPage(page);
		for(BoardVO board : list) {
			logger.info(board.getBno() + " : "  + board.getTitle()+ " : " + board.getContent());
		}
		//System.out.println(list);
	}
	
	
	
	

}
