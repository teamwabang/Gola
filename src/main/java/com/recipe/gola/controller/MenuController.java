package com.recipe.gola.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.recipe.gola.dto.MenuDTO;
import com.recipe.gola.service.MenuService;

import lombok.Data;

@Controller
@Data
public class MenuController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private final MenuService menuService;
	
	// 메뉴 전체리스트
	@GetMapping("menu")
	public String menulist(Model model) {
		logger.info("-----> 메뉴리스트를 출력합니다.");
		List<MenuDTO> menulist = menuService.menulist();
		model.addAttribute("menulist", menulist);
		return "menu/menulist";
	}
	
	// 각 메뉴 상세페이지
	@GetMapping("menu/detail")
	public String detail(@RequestParam int no, Model model) {
		MenuDTO dto = menuService.detail(no);
		model.addAttribute("dto", dto);
		return "menu/detail";
	}
}
