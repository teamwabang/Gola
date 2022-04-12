package com.recipe.gola.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import com.recipe.gola.dto.*;
import com.recipe.gola.mapper.BoardMapper;
import com.recipe.gola.mapper.CommentMapper;
import com.recipe.gola.mapper.UserMapper;

import lombok.Data;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
@Data
public class CommentService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private final CommentMapper commentMapper;
	private final BoardMapper boardMapper;
	
	public CommentService(CommentMapper commentMapper, BoardMapper boardMapper) {
		this.commentMapper = commentMapper;
		this.boardMapper = boardMapper;
	}

    public int getCount(Integer bno) throws Exception {
        return commentMapper.count(bno);
    }


    @Transactional(rollbackFor = Exception.class)
    public int remove(Integer cno, Integer bno, String commenter) throws Exception {
    	
    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	
    	paramMap.put("bno", bno);
    	paramMap.put("cnt", -1);
    	
		int rowCnt = boardMapper.updateCommentCnt(paramMap);
        System.out.println("updateCommentCnt - rowCnt = " + rowCnt);
//        throw new Exception("test");

        
    	Map<String, Object> commentParamMap = new HashMap<String, Object>();
    	
    	commentParamMap.put("cno", cno);
    	commentParamMap.put("commenter", commenter);
   
    	System.out.println(" commentParamMap   :  "+commentParamMap.toString());
        
        rowCnt = commentMapper.delete(commentParamMap);
        System.out.println("rowCnt = " + rowCnt);
        return rowCnt;
    }

    @Transactional(rollbackFor = Exception.class)
    public int write(CommentDTO commentDto) throws Exception {

    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	
    	paramMap.put("bno", commentDto.getBno());
    	paramMap.put("cnt", 1);
    	boardMapper.updateCommentCnt(paramMap);
//                throw new Exception("test");
        return commentMapper.insert(commentDto);
    }

    public List<CommentDTO> getList(Integer bno) throws Exception {
//        throw new Exception("test");
        return commentMapper.selectAll(bno);
    }

    public CommentDTO read(Integer cno) throws Exception {
        return commentMapper.select(cno);
    }

    public int modify(CommentDTO commentDto) throws Exception {
        return commentMapper.update(commentDto);
    }
}
