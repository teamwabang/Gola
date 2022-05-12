package com.recipe.gola.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.Data;

@Controller
@Data
public class DietController {
	
	@Value("${file.upload.directory}")
	private String fileUploadPath;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//칼로리 페이지 
	@GetMapping("cal")
	public String diet_index() {
		logger.info("-----> 칼로리 페이지로 이동합니다.");
		return "diet/cal";
	}
	
	// 식단 추천 페이지
	@GetMapping("diet")
	public String diet_diet() {
		logger.info("-----> 식단 페이지로 이동합니다.");
		return "diet/diet";
	}

}
