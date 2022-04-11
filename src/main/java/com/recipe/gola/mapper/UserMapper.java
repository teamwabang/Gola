package com.recipe.gola.mapper;

import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Mapper;

import com.recipe.gola.dto.UserDTO;

@Mapper
public interface UserMapper {
	
	// 관리자 회원 전체리스트 출력
	public List<UserDTO> userlist();
	
	// 회원가입
	public int insertuser(@Valid UserDTO dto);
	
	// 로그인
	UserDTO findByUsername(String userId);
	
	// 마이페이지 정보조회
	public UserDTO infouser(String userId);
	
	// 마이페이지 회원정보 수정
	public int updateuser(@Valid UserDTO dto);
	
}
