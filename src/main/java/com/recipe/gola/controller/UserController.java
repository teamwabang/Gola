package com.recipe.gola.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.recipe.gola.dto.UserDTO;
import com.recipe.gola.service.UserService;

import lombok.Data;

@Controller
@Data
@RequestMapping("user")
public class UserController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
			
	private final UserService userService;
	
	// 회원목록
	@GetMapping("list")
	public String userlist(Model model) {
		logger.info("전체 회원목록을 출력합니다.");
		List<UserDTO> userlist = userService.userlist();
		model.addAttribute("userlist", userlist);
		return "user/userlist";
	}
	
	// 01 - 회원가입
	@GetMapping("join")
	public String userjoin() {
		logger.info("회원가입을 시도 중 입니다.");
		return "user/join";
	}
	
	@PostMapping("join")
	public String userjoin(@Valid UserDTO dto, Errors errors, Model model) {
        if (errors.hasErrors()) {
            // 회원가입 실패시, 입력 데이터를 유지
            model.addAttribute("insertuser", dto);

            // 유효성 통과 못한 필드와 메시지를 핸들링
            Map<String, String> validatorResult = userService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }

            logger.error("회원가입에 실패하였습니다.");
            return "user/join";
        }
        
        logger.info("회원가입에 성공하였습니다.");
        userService.insertuser(dto);
        return "redirect:/";
    }
}
