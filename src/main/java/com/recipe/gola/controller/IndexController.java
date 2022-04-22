package com.recipe.gola.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.Data;

/**
 * 
 * Created by Team Gola on 2022-04-01
 * 
 * @author 
 * @author 
 * @author 
 * @author 
 * @author 
 *
 */
@Controller
@Data
public class IndexController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/")
	public String index() {
		logger.info("-----> 우리들의 모든 레시피 | 골라");
		return "index";
	}
}
