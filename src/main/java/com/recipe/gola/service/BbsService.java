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

    //게시글번호 생성
    public String selectBno() throws Exception{
    	return bbsMapper.selectBno();
    }
    
	//파일 저장
    public int insertFiles(FilesDTO filesDto) throws Exception{
    	return bbsMapper.insertFiles(filesDto);    	
    }
	
    //파일 삭제
    public int deleteFiles(String fno) throws Exception{
    	return bbsMapper.deleteFiles(fno);    	    	
    }
    
	//파일정보 목록조회
    public List<FilesDTO> selectListFiles(String bno) throws Exception{
    	return bbsMapper.selectListFiles(bno);    	    	
    }
	
	//파일정보 상세조회
    public FilesDTO selectDetailFiles(String fno) throws Exception{
    	return bbsMapper. selectDetailFiles(fno);    	    	
    }
    
}