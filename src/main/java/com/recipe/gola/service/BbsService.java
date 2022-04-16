package com.recipe.gola.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recipe.gola.dto.*;
import com.recipe.gola.mapper.BbsMapper;
import com.recipe.gola.mapper.CommentMapper;
import com.recipe.gola.mapper.UserMapper;

import lombok.Data;

@Service
@Data
public class BbsService {
	
	private final BbsMapper bbsMapper;

	//게시판목록
    public List<BbsDTO> selectListBbs(BbsDTO bbsDTO) throws Exception {
        return bbsMapper.selectListBbs(bbsDTO);
    }

    //게시판 상세조회
    public BbsDTO selectDetailBbs(BbsDTO bbsDTO) throws Exception {
        return bbsMapper.selectDetailBbs(bbsDTO);
    }

    //게시판 저장
    public int insertBbs(BbsDTO bbsDTO) throws Exception {
        return bbsMapper.insertBbs(bbsDTO);
    }
    
    //게시판 삭제
    public int deleteBbs(BbsDTO bbsDTO) throws Exception {
        return bbsMapper.deleteBbs(bbsDTO);
    }

    //게시판 수정
    public int updateBbs(BbsDTO bbsDTO) throws Exception {
        return bbsMapper.updateBbs(bbsDTO);
    }

}