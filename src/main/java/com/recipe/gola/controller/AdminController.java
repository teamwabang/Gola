package com.recipe.gola.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.recipe.gola.dto.UserDTO;
import com.recipe.gola.service.UserService;

import lombok.Data;

@Controller
@Data
public class AdminController {

private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private final UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	// 회원목록
	@GetMapping("admin/list")
	public String userlist(Model model) {
		logger.info("전체 회원목록을 출력합니다.");
		List<UserDTO> userlist = userService.userlist();
		model.addAttribute("userlist", userlist);
		return "admin/userlist";
	}
}
