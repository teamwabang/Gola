package com.recipe.gola.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.recipe.gola.dto.RecipeDTO;
import com.recipe.gola.dto.RecipeLinkDTO;
import com.recipe.gola.service.RecipeLinkService;
import com.recipe.gola.service.RecipeService;

import lombok.Data;

@Controller
@Data
public class RecipeController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private final RecipeService recipeService;
	
	@Autowired
	private final RecipeLinkService recipelinkService;
	
	// 메뉴 전체리스트
	@GetMapping("recipe")
	public String list(Model model) {
		logger.info("-----> 전체 레시피 리스트를 출력합니다.");
		List<RecipeDTO> list = recipeService.list();
		model.addAttribute("list", list);
		return "recipe/list";
	}
	
	// 각 메뉴 상세페이지
	@GetMapping("recipe/detail")
	public String detail(RecipeDTO dto, RecipeLinkDTO linkdto, Model model) {
		logger.info("-----> " + dto.getNo() + "번 메뉴를 출력합니다.");
		dto = recipeService.detail(dto);
		linkdto = recipelinkService.detailLink(linkdto);
		model.addAttribute("detail", dto);
		model.addAttribute("detaillink", linkdto);
		return "recipe/detail";
	}
}
