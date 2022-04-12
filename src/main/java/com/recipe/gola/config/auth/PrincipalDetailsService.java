package com.recipe.gola.config.auth;

import java.util.List;

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
public class PrincipalDetailsService implements UserDetailsService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private final UserMapper userMapper;
	
	// 회원목록
	public List<UserDTO> userlist() {
		return userMapper.userlist();
	}
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		UserDTO dto = userMapper.findByUsername(userId);
		if(dto != null) {
			return new PrincipalDetails(dto);
		}
		return null;
	}
	
	// 회원가입
	public int insertuser(UserDTO dto) {
		return userMapper.insertuser(dto);
	}
	
	// 마이페이지 정보조회
	public UserDTO infouser(String userId) {
		return userMapper.infouser(userId);
	}
	
	// 마이페이지 회원정보 수정
	public void updateuser(UserDTO dto) {
		logger.info("수정된 회원정보 : " + dto);
		userMapper.updateuser(dto);
	}


}