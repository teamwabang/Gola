package com.recipe.gola.mapper;

import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Mapper;

import com.recipe.gola.dto.UserDTO;

@Mapper
public interface UserMapper {
	
	// 회원 목록
	public List<UserDTO> userlist();
	
	// 01 - 회원가입
	public int insertuser(@Valid UserDTO dto);
	
	// 02 - 로그인
	public UserDTO findByUserId(String userId);
	
}
