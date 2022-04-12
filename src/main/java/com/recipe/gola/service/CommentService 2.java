package com.recipe.gola.service;

import org.springframework.transaction.annotation.Transactional;

import com.recipe.gola.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    int getCount(Integer bno) throws Exception;

    @Transactional(rollbackFor = Exception.class)
    int remove(Integer cno, Integer bno, String commenter) throws Exception;

    @Transactional(rollbackFor = Exception.class)
    int write(CommentDTO commentDto) throws Exception;

    List<CommentDTO> getList(Integer bno) throws Exception;

    CommentDTO read(Integer cno) throws Exception;

    int modify(CommentDTO commentDto) throws Exception;
}
