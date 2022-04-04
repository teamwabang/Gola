package com.recipe.gola.security.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.recipe.gola.dto.UserDTO;
import com.recipe.gola.mapper.UserMapper;

@Service
public class PrincipalDetailsService implements UserDetailsService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDTO dto = userMapper.findByUsername(username);
		if(dto != null) {
			return new PrincipalDetails(dto);
		}
		return null;
	}
	
}
