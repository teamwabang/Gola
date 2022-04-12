package com.recipe.gola.dao;


import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.recipe.gola.dto.BoardDTO;
import com.recipe.gola.mapper.BoardMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class boardMapperTest {
    @Autowired
    private BoardMapper boardMapper;


    @Test
    public void insertTestData() throws Exception {
    	boardMapper.deleteAll();
        for (int i = 1; i <= 220; i++) {
            BoardDTO boardDto = new BoardDTO("title" + i, "content", "asdf");
            boardMapper.insert(boardDto);
        }
    }
    @Test
    public void countTest() throws Exception {
    	boardMapper.deleteAll();
        assertTrue(boardMapper.count()==0);

        BoardDTO boardDto = new BoardDTO("no title", "no content", "asdf");
        assertTrue(boardMapper.insert(boardDto)==1);
        assertTrue(boardMapper.count()==1);

        assertTrue(boardMapper.insert(boardDto)==1);
        assertTrue(boardMapper.count()==2);
    }

    @Test
    public void deleteAllTest() throws Exception {
    	boardMapper.deleteAll();
        assertTrue(boardMapper.count()==0);

        BoardDTO boardDto = new BoardDTO("no title", "no content", "asdf");
        assertTrue(boardMapper.insert(boardDto)==1);
        assertTrue(boardMapper.deleteAll()==1);
        assertTrue(boardMapper.count()==0);

        boardDto = new BoardDTO("no title", "no content", "asdf");
        assertTrue(boardMapper.insert(boardDto)==1);
        assertTrue(boardMapper.insert(boardDto)==1);
        assertTrue(boardMapper.deleteAll()==2);
        assertTrue(boardMapper.count()==0);
    }

    @Test
    public void deleteTest() throws Exception {
    	boardMapper.deleteAll();
        assertTrue(boardMapper.count()==0);

        BoardDTO boardDto = new BoardDTO("no title", "no content", "asdf");
        assertTrue(boardMapper.insert(boardDto)==1);
        Integer bno = boardMapper.selectAll().get(0).getBno();
        assertTrue(boardMapper.delete(bno, boardDto.getWriter())==1);
        assertTrue(boardMapper.count()==0);

        assertTrue(boardMapper.insert(boardDto)==1);
        bno = boardMapper.selectAll().get(0).getBno();
        assertTrue(boardMapper.delete(bno, boardDto.getWriter()+"222")==0);
        assertTrue(boardMapper.count()==1);

        assertTrue(boardMapper.delete(bno, boardDto.getWriter())==1);
        assertTrue(boardMapper.count()==0);

        assertTrue(boardMapper.insert(boardDto)==1);
        bno = boardMapper.selectAll().get(0).getBno();
        assertTrue(boardMapper.delete(bno+1, boardDto.getWriter())==0);
        assertTrue(boardMapper.count()==1);
    }

    @Test
    public void insertTest() throws Exception {
        boardMapper.deleteAll();
        BoardDTO boardDto = new BoardDTO("no title", "no content", "asdf");
        assertTrue(boardMapper.insert(boardDto)==1);

        boardDto = new BoardDTO("no title", "no content", "asdf");
        assertTrue(boardMapper.insert(boardDto)==1);
        assertTrue(boardMapper.count()==2);

        boardMapper.deleteAll();
        boardDto = new BoardDTO("no title", "no content", "asdf");
        assertTrue(boardMapper.insert(boardDto)==1);
        assertTrue(boardMapper.count()==1);
    }

    @Test
    public void selectAllTest() throws Exception {
        boardMapper.deleteAll();
        assertTrue(boardMapper.count()==0);

        List<BoardDTO> list = boardMapper.selectAll();
        assertTrue(list.size() == 0);

        BoardDTO boardDto = new BoardDTO("no title", "no content", "asdf");
        assertTrue(boardMapper.insert(boardDto)==1);

        list = boardMapper.selectAll();
        assertTrue(list.size() == 1);

        assertTrue(boardMapper.insert(boardDto)==1);
        list = boardMapper.selectAll();
        assertTrue(list.size() == 2); 
    }

    @Test
    public void selectTest() throws Exception {
        boardMapper.deleteAll();
        assertTrue(boardMapper.count()==0);

        BoardDTO boardDto = new BoardDTO("no title", "no content", "asdf");
        assertTrue(boardMapper.insert(boardDto)==1);

        Integer bno = boardMapper.selectAll().get(0).getBno();
        boardDto.setBno(bno);
        BoardDTO boardDto2 = boardMapper.select(bno);
        assertTrue(boardDto.equals(boardDto2));
    }

    @Test
    public void selectPageTest() throws Exception {
        boardMapper.deleteAll();

        for (int i = 1; i <= 10; i++) {
            BoardDTO boardDto = new BoardDTO(""+i, "no content"+i, "asdf");
            boardMapper.insert(boardDto);
        }

        Map map = new HashMap();
        map.put("offset", 0);
        map.put("pageSize", 3);

        List<BoardDTO> list = boardMapper.selectPage(map);
        assertTrue(list.get(0).getTitle().equals("10"));
        assertTrue(list.get(1).getTitle().equals("9"));
        assertTrue(list.get(2).getTitle().equals("8"));

        map = new HashMap();
        map.put("offset", 0);
        map.put("pageSize", 1);

        list = boardMapper.selectPage(map);
        assertTrue(list.get(0).getTitle().equals("10"));

        map = new HashMap();
        map.put("offset", 7);
        map.put("pageSize", 3);

        list = boardMapper.selectPage(map);
        assertTrue(list.get(0).getTitle().equals("3"));
        assertTrue(list.get(1).getTitle().equals("2"));
        assertTrue(list.get(2).getTitle().equals("1"));
    }

    @Test
    public void updateTest() throws Exception {
        boardMapper.deleteAll();
        BoardDTO boardDto = new BoardDTO("no title", "no content", "asdf");
        assertTrue(boardMapper.insert(boardDto)==1);

        Integer bno = boardMapper.selectAll().get(0).getBno();
        System.out.println("bno = " + bno);
        boardDto.setBno(bno);
        boardDto.setTitle("yes title");
        assertTrue(boardMapper.update(boardDto)==1);

        BoardDTO boardDto2 = boardMapper.select(bno);
        assertTrue(boardDto.equals(boardDto2));
    }

    @Test
    public void increaseViewCntTest() throws Exception {
        boardMapper.deleteAll();
        assertTrue(boardMapper.count()==0);

        BoardDTO boardDto = new BoardDTO("no title", "no content", "asdf");
        assertTrue(boardMapper.insert(boardDto)==1);
        assertTrue(boardMapper.count()==1);

        Integer bno = boardMapper.selectAll().get(0).getBno();
        assertTrue(boardMapper.increaseViewCnt(bno)==1);

        boardDto = boardMapper.select(bno);
        assertTrue(boardDto!=null);
        assertTrue(boardDto.getView_cnt() == 1);

        assertTrue(boardMapper.increaseViewCnt(bno)==1);
        boardDto = boardMapper.select(bno);
        assertTrue(boardDto!=null);
        assertTrue(boardDto.getView_cnt() == 2);
    }
}