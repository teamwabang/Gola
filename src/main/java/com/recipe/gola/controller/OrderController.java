package com.recipe.gola.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.recipe.gola.service.RecipeLinkService;
import com.recipe.gola.service.ShopService;

import lombok.Data;

@Controller
@Data
@RequestMapping("shop")
public class OrderController {
	
	@Value("${file.upload.directory}")
	private String fileUploadPath;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private final ShopService ShopService;
	
	@Autowired
	private final RecipeLinkService recipelinkService;
	
	// 주문서작성/결제 
	@GetMapping("order")
	public String shop_order() {
		logger.info("-----> 주문서작성/결제 페이지로 이동합니다.");
		return "shop/order";
	}
	
	// 주문완료
	@GetMapping("order/result")
	public String shop_order_result() {
		logger.info("-----> 주문이 정상적으로 완료되었습니다.");
		return "shop/order_result";
	}

}