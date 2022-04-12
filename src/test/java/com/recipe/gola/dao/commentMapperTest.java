package com.recipe.gola.dao;


import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;

import com.recipe.gola.dto.CommentDTO;
import com.recipe.gola.mapper.CommentMapper;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class commentMapperTest {
    @Autowired
     CommentMapper commentMapper;

    @Test
    public void count() throws Exception {
        commentMapper.deleteAll(1);
        assertTrue(commentMapper.count(1)==0);
    }

    @Test
    public void delete() throws Exception {
        commentMapper.deleteAll(1);
        CommentDTO commentDto = new CommentDTO(1, 0, "comment", "asdf");
        assertTrue(commentMapper.insert(commentDto)==1);
        assertTrue(commentMapper.count(1)==1);
    }

    @Test
    public void insert() throws Exception {
        commentMapper.deleteAll(1);
        CommentDTO commentDto = new CommentDTO(1, 0, "comment", "asdf");
        assertTrue(commentMapper.insert(commentDto)==1);
        assertTrue(commentMapper.count(1)==1);

        commentDto = new CommentDTO(1, 0, "comment", "asdf");
        assertTrue(commentMapper.insert(commentDto)==1);
        assertTrue(commentMapper.count(1)==2);
    }

    @Test
    public void selectAll() throws Exception {
        commentMapper.deleteAll(1);
        CommentDTO commentDto = new CommentDTO(1, 0, "comment", "asdf");
        assertTrue(commentMapper.insert(commentDto)==1);
        assertTrue(commentMapper.count(1)==1);

        List<CommentDTO> list = commentMapper.selectAll(1);
        assertTrue(list.size()==1);

        commentDto = new CommentDTO(1, 0, "comment", "asdf");
        assertTrue(commentMapper.insert(commentDto)==1);
        assertTrue(commentMapper.count(1)==2);

        list = commentMapper.selectAll(1);
        assertTrue(list.size()==2);
    }

    @Test
    public void select() throws Exception {
        commentMapper.deleteAll(1);
        CommentDTO commentDto = new CommentDTO(1, 0, "comment", "asdf");
        assertTrue(commentMapper.insert(commentDto)==1);
        assertTrue(commentMapper.count(1)==1);

        List<CommentDTO> list = commentMapper.selectAll(1);
        String comment = list.get(0).getComment();
        String commenter = list.get(0).getCommenter();
        assertTrue(comment.equals(commentDto.getComment()));
        assertTrue(commenter.equals(commentDto.getCommenter()));
    }

    @Test
    public void update() throws Exception {
        commentMapper.deleteAll(1);
        CommentDTO commentDto = new CommentDTO(1, 0, "comment", "asdf");
        assertTrue(commentMapper.insert(commentDto)==1);
        assertTrue(commentMapper.count(1)==1);

        List<CommentDTO> list = commentMapper.selectAll(1);
        commentDto.setCno(list.get(0).getCno());
        commentDto.setComment("comment2");
        assertTrue(commentMapper.update(commentDto)==1);

        list = commentMapper.selectAll(1);
        String comment = list.get(0).getComment();
        String commenter = list.get(0).getCommenter();
        assertTrue(comment.equals(commentDto.getComment()));
        assertTrue(commenter.equals(commentDto.getCommenter()));
    }
}
