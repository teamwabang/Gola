package com.recipe.gola;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.recipe.gola.dto.UserDTO;
import com.recipe.gola.mapper.UserMapper;

@SpringBootTest
public class TestApplication {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserMapper userMapper;
	
	
//	@Test
//	void test() {
//		logger.debug("[DEBUG]");
//		logger.info("[INFO]");
//		logger.warn("[WARN]");
//		logger.error("[ERROR]");
//	}
	
	@Test
	void join() {
		UserDTO dto = new UserDTO();
		
		dto.setUserId("test3");
		dto.setUserPwd("testtest1!");
		dto.setUserNickname("test3");
		dto.setUserEmail("test3@test.com");
		
		int newUser = userMapper.insertuser(dto);
	}
}
