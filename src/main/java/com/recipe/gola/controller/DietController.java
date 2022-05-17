package com.recipe.gola.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.recipe.gola.service.BbsService;
import com.recipe.gola.service.DietService;

import lombok.Data;

@Controller
@Data
public class DietController {
	
	@Value("${file.upload.directory}")
	private String fileUploadPath;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	DietService dietService;
	
	// 칼로리 페이지 
	@GetMapping("cal")
	public String diet_cal() {
		logger.info("-----> 칼로리 페이지로 이동합니다.");
		return "diet/cal";
	}

	// 식단 추천 페이지 
	@GetMapping("diet")
	public String recmnd_menu(Model m, @RequestParam String kcal, HttpSession session) throws Exception {
		
		logger.info("-----> 식단 추천페이지 입니다.");
		logger.info("페이지에서 넘어온 칼로리 ? >>>>>>>>> "+kcal);

		String keyword = "";
		
		
		if(null != session) {
			if(session.getAttribute("keyword") != null){
				keyword = session.getAttribute("keyword").toString();
			}			
		}
		
		
		//아침식단
		m.addAttribute("breakfastList",dietService.list(kcal,"B", keyword));
		//점심식단
		m.addAttribute("lunchList",dietService.list(kcal,"L", keyword));
		//저녁식단
		m.addAttribute("dinnerList",dietService.list(kcal,"D", keyword));
		
		return "diet/diet";
	}	
}
