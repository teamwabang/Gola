package com.recipe.gola.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.recipe.gola.dto.ProductDTO;
import com.recipe.gola.service.ShopService;

import lombok.Data;

@Controller
@Data
public class ShopController {
	
	@Value("${file.upload.directory}")
	private String fileUploadPath;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private final ShopService shopService;
	
	
	// 쇼핑몰 페이지 
	@GetMapping("shop")
	public String shoplist(Model model, ProductDTO dto) {
		logger.info("-----> 쇼핑몰 페이지로 이동하여 전체 리스트를 출력합니다.");
		List<ProductDTO> shopalllist = shopService.shopalllist(dto);
		List<ProductDTO> shopdiscountlist = shopService.shopdiscountlist(dto);
		model.addAttribute("shopalllist", shopalllist);
		model.addAttribute("shopdiscountlist", shopdiscountlist);
		return "shop/shop";
	}

	// 쇼핑몰 상세페이지 
	@GetMapping("shop/detail")
	public String shopdetail(ProductDTO dto, Model model) {
		logger.info("-----> " + dto.getPno() + "번 재료를 출력합니다.");
		dto = shopService.shopdetail(dto);
		model.addAttribute("shopdetail", dto);
		return "shop/shopdetail";
	}

}