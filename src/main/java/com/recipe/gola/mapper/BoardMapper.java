package com.recipe.gola.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.recipe.gola.dto.BoardDTO;
import com.recipe.gola.dto.SearchCondition;

@Mapper
public interface BoardMapper {
	
    BoardDTO select(Integer bno) throws Exception;
    int delete(Integer bno, String writer) throws Exception;
    int insert(BoardDTO dto) throws Exception;
    int update(BoardDTO dto) throws Exception;
    int increaseViewCnt(Integer bno) throws Exception;

    List<BoardDTO> selectPage(Map map) throws Exception;
    List<BoardDTO> selectAll() throws Exception;

    BoardDTO select(int bno);

    int deleteAll() throws Exception;
    int count() throws Exception;

    int searchResultCnt(SearchCondition sc) throws Exception;
    List<BoardDTO> searchSelectPage(SearchCondition sc) throws Exception;

    int updateCommentCnt(Integer bno, int i);
    
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
