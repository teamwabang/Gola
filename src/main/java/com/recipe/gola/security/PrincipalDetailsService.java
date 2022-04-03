package com.recipe.gola.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.recipe.gola.dto.UserDTO;
import com.recipe.gola.mapper.UserMapper;

@Service
public class PrincipalDetailsService implements UserDetailsService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		UserDTO dto = userMapper.findByUserId(userId);
		if(dto != null) {
			return new PrincipalDetails(dto);
		}
		return null;
	}
	
}
