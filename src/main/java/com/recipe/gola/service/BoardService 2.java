package com.recipe.gola.service;

import java.util.List;
import java.util.Map;

import com.recipe.gola.dto.BoardDTO;
import com.recipe.gola.dto.SearchCondition;

public interface BoardService{

	int getCount() throws Exception;

	int remove(Integer bno, String writer) throws Exception;

	int write(BoardDTO boardDto) throws Exception;

	List<BoardDTO> getList() throws Exception;

	BoardDTO read(Integer bno) throws Exception;

	List<BoardDTO> getPage(Map map) throws Exception;

	int modify(BoardDTO boardDto) throws Exception;

	int getSearchResultCnt(SearchCondition sc) throws Exception;

	List<BoardDTO> getSearchResultPage(SearchCondition sc) throws Exception;

}