package com.recipe.gola.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.recipe.gola.dto.BbsDTO;
import com.recipe.gola.dto.FilesDTO;

@Mapper
public interface BbsMapper {

	//게시판 목록조회
	List<BbsDTO> selectListBbs(BbsDTO bbsDTO) throws Exception;
	
	//게시판 상세조회
	BbsDTO selectDetailBbs(BbsDTO bbsDTO) throws Exception;

	//게시판 저장
	int insertBbs(BbsDTO bbsDTO) throws Exception;
	
    //게시판 삭제
    int deleteBbs(BbsDTO bbsDTO) throws Exception;
    
    //게시판 수정
    int updateBbs(BbsDTO bbsDTO) throws Exception;

    //게시글번호 생성
    String selectBno() throws Exception;
    
	//파일 저장
	int insertFiles(FilesDTO filesDto) throws Exception;
	
    //파일 삭제
    int deleteFiles(String fno) throws Exception;
    
	//파일정보 목록조회
	List<FilesDTO> selectListFiles(String bno) throws Exception;
	
	//파일정보 상세조회
	FilesDTO selectDetailFiles(String fno) throws Exception;
	
    int updateCommentCnt(Map<String, Object> paramMap);
    
    
    
}
