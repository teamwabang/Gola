package com.recipe.gola.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import com.recipe.gola.dto.BbsDTO;
import com.recipe.gola.dto.CommentDTO;

@Mapper
public interface CommentMapper {
	
    int count(Integer bno) throws Exception // T selectOne(String statement)
    ;

    int deleteAll(Integer bno) // int delete(String statement)
    ;

    int delete(Map<String, Object> map) throws Exception // int delete(String statement, Object parameter)
    ;

    int insert(CommentDTO dto) throws Exception // int insert(String statement, Object parameter)
    ;

    List<CommentDTO> selectAll(Integer bno) throws Exception // List<E> selectList(String statement)
    ;

    CommentDTO select(Integer cno) throws Exception // T selectOne(String statement, Object parameter)
    ;

    int update(CommentDTO dto) throws Exception // int update(String statement, Object parameter)
    ;
    
    int getCount(Integer bno) throws Exception;

    @Transactional(rollbackFor = Exception.class)
    int remove(Integer cno, Integer bno, String commenter) throws Exception;

    @Transactional(rollbackFor = Exception.class)
    int write(CommentDTO commentDto) throws Exception;

    List<CommentDTO> getList(Integer bno) throws Exception;

    CommentDTO read(Integer cno) throws Exception;

    int modify(CommentDTO commentDto) throws Exception;

}
