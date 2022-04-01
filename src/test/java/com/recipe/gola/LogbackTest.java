package com.recipe.gola;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LogbackTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	void test() {
		logger.debug("[DEBUG]");
		logger.info("[INFO]");
		logger.warn("[WARN]");
		logger.error("[ERROR]");
	}
}
