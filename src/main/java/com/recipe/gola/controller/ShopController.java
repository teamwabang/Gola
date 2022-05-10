package com.recipe.gola.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.Data;

@Controller
@Data
public class ShopController {
	
	@Value("${file.upload.directory}")
	private String fileUploadPath;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 회원가입
	@GetMapping("shop")
	public String shop_index() {
		logger.info("-----> 쇼핑몰 페이지로 이동합니다.");
		return "shop/shop";
	}
	
}