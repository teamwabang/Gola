package com.recipe.gola.service;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;

import com.recipe.gola.dao.*;
import com.recipe.gola.dto.*;
import com.recipe.gola.mapper.BoardMapper;
import com.recipe.gola.mapper.CommentMapper;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CommentServiceTest {
    @Autowired
    CommentService commentService;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    BoardMapper boardMapper;

    @Test
    public void remove() throws Exception {
        boardMapper.deleteAll();

        BoardDTO boardDto = new BoardDTO("hello", "hello", "asdf");
        assertTrue(boardMapper.insert(boardDto) == 1);
        Integer bno = boardMapper.selectAll().get(0).getBno();
        System.out.println("bno = " + bno);

        commentMapper.deleteAll(bno);
        CommentDTO commentDto = new CommentDTO(bno,0,"hi","qwer");

        assertTrue(boardMapper.select(bno).getComment_cnt() == 0);
        assertTrue(commentService.write(commentDto)==1);
        assertTrue(boardMapper.select(bno).getComment_cnt() == 1);

        Integer cno = commentMapper.selectAll(bno).get(0).getCno();

        // 일부러 예외를 발생시키고 Tx가 취소되는지 확인해야.
        int rowCnt = commentService.remove(cno, bno, commentDto.getCommenter());
        assertTrue(rowCnt==1);
        assertTrue(boardMapper.select(bno).getComment_cnt() == 0);
    }

    @Test
    public void write() throws  Exception {
        boardMapper.deleteAll();

        BoardDTO boardDto = new BoardDTO("hello", "hello", "asdf");
        assertTrue(boardMapper.insert(boardDto) == 1);
        Integer bno = boardMapper.selectAll().get(0).getBno();
        System.out.println("bno = " + bno);

        commentMapper.deleteAll(bno);
        CommentDTO commentDto = new CommentDTO(bno,0,"hi","qwer");

        assertTrue(boardMapper.select(bno).getComment_cnt() == 0);
        assertTrue(commentService.write(commentDto)==1);

        Integer cno = commentMapper.selectAll(bno).get(0).getCno();
        assertTrue(boardMapper.select(bno).getComment_cnt() == 1);
    }
}