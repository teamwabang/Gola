package com.recipe.gola.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recipe.gola.dto.UserDTO;
import com.recipe.gola.mapper.UserMapper;

import lombok.Data;

@Service
@Data
public class UserService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private final UserMapper userMapper;
	
	// 회원목록
	public List<UserDTO> userlist() {
		return userMapper.userlist();
	}
	
	// 회원가입
	public int insertuser(UserDTO dto) {
		return userMapper.insertuser(dto);
	}
	
	// 아이디 중복확인
	public int idCheck(String userId) {
		return userMapper.idCheck(userId);
	}
	
	// 마이페이지 정보조회
	public UserDTO infouser(String userId) {
		return userMapper.infouser(userId);
	}
	
	// 마이페이지 회원정보 수정
	public void modify(UserDTO dto) {
		userMapper.modify(dto);
	}

	// 회원탈퇴
	public void remove(String userId) {
		userMapper.remove(userId);
	}

}