package com.recipe.gola;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.recipe.gola.config.auth.UserAuth;
import com.recipe.gola.dto.UserDTO;
import com.recipe.gola.mapper.UserMapper;

@SpringBootTest
public class UserTestApplication {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Test
	void join() {
		UserDTO dto = new UserDTO();
		
		dto.setUserId("test1");
		
		String rawPwd = "Ttesttest1!";
        String encPwd = bCryptPasswordEncoder.encode(rawPwd);
        dto.setUserPwd(encPwd);
		dto.setUserNickname("111111");
		dto.setUserEmail("11111@test.com");
		dto.setUserAuth(UserAuth.USER);
		
		int newUser = userMapper.insertuser(dto);
	}
}
