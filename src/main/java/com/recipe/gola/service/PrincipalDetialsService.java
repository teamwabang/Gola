package com.recipe.gola.service;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.recipe.gola.dto.UserDTO;
import com.recipe.gola.mapper.UserMapper;

import lombok.Data;

@Service
@Data
public class PrincipalDetialsService implements UserDetailsService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private final UserMapper userMapper;
	
	// 회원목록
	public List<UserDTO> userlist() {
		return userMapper.userlist();
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDTO dto = userMapper.findByUsername(username);
		if(dto != null) {
			return new PrincipalDetails(dto);
		}
		return null;
	}
	
	// 회원가입
	public int insertuser(@Valid UserDTO dto) {
		return userMapper.insertuser(dto);
	}
	
	// 마이페이지 정보조회
	public UserDTO infouser(String userId) {
		return userMapper.infouser(userId);
	}
	
	// 마이페이지 회원정보 수정
	public int updateuser(@Valid UserDTO dto) {
		logger.info("수정된 회원정보 : " + dto);
		return userMapper.updateuser(dto);
	}


}