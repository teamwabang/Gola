package com.recipe.gola.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.recipe.gola.dto.BbsDTO;
import com.recipe.gola.service.BbsService;

import lombok.Data;

/**
 * 
 * Created by Team Gola on 2022-04-01
 * 
 * @author 김율리아
 * @author 박병권
 * @author 엄지혜
 * @author 정재홍
 * @author 한상민
 *
 */
@Controller
@Data
public class IndexController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    BbsService bbsService;
	
	@GetMapping("/")
	public String index(Model m, BbsDTO bbsDto) throws Exception{
		logger.info("-----> 우리들의 모든 레시피 | 골라");
		
		bbsDto.setIndexYn("Y");
		m.addAttribute("list", bbsService.selectListBbs(bbsDto));
	
		return "index";
	}
	
	@GetMapping("about")
	public String about() {
		logger.info("-----> 소개합니다, 우리가 만든 이야기를 | 골라");
		return "about/teamGOLA";
	}
}
