package com.recipe.gola.config.auth;

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
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		UserDTO dto = userMapper.findByUsername(userId);
		if(dto != null) {
			return new PrincipalDetails(dto);
		}
		return null;
	}


}