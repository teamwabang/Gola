package com.recipe.gola.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recipe.gola.dto.*;
import com.recipe.gola.mapper.BoardMapper;
import com.recipe.gola.mapper.CommentMapper;
import com.recipe.gola.mapper.UserMapper;

import lombok.Data;

@Service
@Data
public class BoardService {
	
	private final BoardMapper boardMapper;

    public int getCount() throws Exception {
        return boardMapper.count();
    }

    public int remove(Integer bno, String writer) throws Exception {
        return boardMapper.delete(bno, writer);
    }

    public int write(BoardDTO boardDto) throws Exception {
        return boardMapper.insert(boardDto);
    }

    public List<BoardDTO> getList() throws Exception {
        return boardMapper.selectAll();
    }

    public BoardDTO read(Integer bno) throws Exception {
        BoardDTO boardDto = boardMapper.select(bno);
        boardMapper.increaseViewCnt(bno);

        return boardDto;
    }

    public List<BoardDTO> getPage(Map map) throws Exception {
        return boardMapper.selectPage(map);
    }

    public int modify(BoardDTO boardDto) throws Exception {
        return boardMapper.update(boardDto);
    }

    public int getSearchResultCnt(SearchCondition sc) throws Exception {
        return boardMapper.searchResultCnt(sc);
    }

    public List<BoardDTO> getSearchResultPage(SearchCondition sc) throws Exception {
        return boardMapper.searchSelectPage(sc);
    }
}