package com.recipe.gola.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.recipe.gola.dto.BbsDTO;

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

}
