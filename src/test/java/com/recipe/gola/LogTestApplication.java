package com.recipe.gola;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.recipe.gola.dto.UserDTO;
import com.recipe.gola.mapper.UserMapper;

@SpringBootTest
public class LogTestApplication {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserMapper userMapper;
	
	
	@Test
	void test() {
		logger.debug("[DEBUG]");
		logger.info("[INFO]");
		logger.warn("[WARN]");
		logger.error("[ERROR]");
	}
	
	@Test
	void join() {
		UserDTO dto = new UserDTO();
		
		dto.setUserId("test2");
		dto.setUserPwd("Ttesttest1!");
		dto.setUserNickname("2222222");
		dto.setUserEmail("test2@test.com");
		
		int newUser = userMapper.insertuser(dto);
	}
}
