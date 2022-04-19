package com.recipe.gola.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.recipe.gola.common.validate.Validate;
import com.recipe.gola.config.auth.PrincipalDetails;
import com.recipe.gola.config.auth.UserAuth;
import com.recipe.gola.dto.BbsDTO;
import com.recipe.gola.dto.UserDTO;
import com.recipe.gola.service.BbsService;
import com.recipe.gola.service.UserService;

import lombok.Data;

@Controller
@Data
public class UserController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private final UserService userService;

	@Autowired
	private final BbsService bbsService;
	
	@Autowired
	private final Validate validate;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	// 회원가입
	@GetMapping("join")
	public String userjoin() {
		logger.info("-----> 회원가입을 시도 중 입니다.");
		return "index";
	}
	
	@PostMapping("join")
	public String userjoin(@Valid UserDTO dto, String userId, String userEmail, Errors errors, Model model) {
//        if (errors.hasErrors()) {
//            // 회원가입 실패시, 입력 데이터를 유지
//            model.addAttribute("insertuser", dto);
//
//            // 유효성 통과 못한 필드와 메시지를 핸들링
//            Map<String, String> validatorResult = validate.validateHandling(errors);
//            for (String key : validatorResult.keySet()) {
//                model.addAttribute(key, validatorResult.get(key));
//            }
//
//            logger.error("=====> 회원가입에 실패하였습니다.");
//            return "redirect:/";
//        }
		int result = userService.idCheck(userId);
		int result2 = userService.emailCheck(userEmail);
		
		try {
			if(result == 1 || result2 == 1) {
				logger.error("=====> 회원가입에 실패하였습니다.");
				model.addAttribute("result", "fail");
			} else if(result == 0 || result == 0) {
				logger.info("-----> 회원가입에 성공하였습니다.");
				String rawPwd = dto.getUserPwd();
				String encPwd = bCryptPasswordEncoder.encode(rawPwd);
				dto.setUserPwd(encPwd);
				userService.insertuser(dto);
			}			
		} catch(Exception e) {
			throw new RuntimeException();
		}
        return "redirect:/";
    }
	
	// 아이디 중복확인
	@PostMapping("/idCheck")
	@ResponseBody
	public int idCheck(String userId) {
		int result = userService.idCheck(userId);
		return result;
	}
	
	// 이메일 중복확인
	@PostMapping("/emailCheck")
	@ResponseBody
	public int emailCheck(String userEmail) {
		int result = userService.emailCheck(userEmail);
		return result;
	}
	
	// 로그인
	@GetMapping("login")
	public String login() {
		return "index";
	}
	
	// 로그아웃
	@GetMapping("logout")
	public ModelAndView logout(HttpSession session) {
		Object object = session.getAttribute("login");
		
		if(object != null) {
			session.removeAttribute("login");
			session.invalidate();
		}
		return new ModelAndView("redirect:/");
	}
	
	// 마이페이지
	@GetMapping("mypage")
	public String mypage(@AuthenticationPrincipal PrincipalDetails principaldetail, Model model) throws Exception {
		logger.info("-----> 마이페이지로 이동합니다.");
		logger.info("유저 아이디 : " + principaldetail.getUsername());
		
		model.addAttribute("dto", principaldetail.getDto());
	    BbsDTO bbsDto = new BbsDTO();
	    bbsDto.setWriter(principaldetail.getUsername());
		model.addAttribute("list",bbsService.selectListBbs(bbsDto));
		
		return "user/mypage";
	}
	
	// 마이페이지 회원정보 수정(비밀번호)
	@PostMapping("mypage/modify/password")
	public String modifyPwd(@AuthenticationPrincipal PrincipalDetails principaldetail, 
			UserDTO dto, HttpSession session) {
		logger.info("-----> 회원 " + principaldetail.getUsername() + "님이 비밀번호를 수정하였습니다.");
		
		dto.setUserAuth(UserAuth.USER);
        
		String rawPwd = dto.getUserPwd();
		String encPwd = bCryptPasswordEncoder.encode(rawPwd);
		dto.setUserPwd(encPwd);
        userService.modifyPwd(dto);

        session.invalidate();
		return "redirect:/mypage";
	}

	// 마이페이지 회원정보 수정(닉네임)
	@PostMapping("mypage/modify/nickname")
	public String modifyNickname(@AuthenticationPrincipal PrincipalDetails principaldetail, 
			UserDTO dto, HttpSession session) {
		logger.info("-----> 회원 " + principaldetail.getUsername() + "님이 닉네임을 수정하였습니다.");
		
		dto.setUserAuth(UserAuth.USER);
        
        userService.modifyNickname(dto);

        session.invalidate();
		return "redirect:/mypage";
	}
	
	// 마이페이지 회원정보 수정(이메일)
		@PostMapping("mypage/modify/email")
		public String modifyEmail(@AuthenticationPrincipal PrincipalDetails principaldetail, 
				UserDTO dto, HttpSession session) {
			logger.info("-----> 회원 " + principaldetail.getUsername() + "님이 이메일을 수정하였습니다.");
			
			dto.setUserAuth(UserAuth.USER);
	        
	        userService.modifyEmail(dto);

	        session.invalidate();
			return "redirect:/mypage";
		}
	
	// 회원탈퇴
	@GetMapping("mypage/leave")
	public String remove(@AuthenticationPrincipal PrincipalDetails principaldetail, 
			Model model, HttpServletResponse response) {
		logger.info("-----> 회원탈퇴 페이지로 이동합니다.");
		logger.info("유저 아이디 : " + principaldetail.getUsername());
		model.addAttribute("dto", principaldetail.getDto());
		return "user/leave";
	}
	
	@PostMapping("mypage/leave")
	public String remove(@AuthenticationPrincipal PrincipalDetails principaldetail, 
			@RequestParam String userId, Model model, HttpSession session, HttpServletResponse response) {
		logger.info("-----> 회원탈퇴가 정상적으로 완료되었습니다.");
		
//		Cookie cookie = new Cookie("remember-me", null);
//		cookie.setMaxAge(0);
//		response.addCookie(cookie);
		
		userService.remove(userId);
		SecurityContextHolder.clearContext();
		session.invalidate();
		return "redirect:/";
	}
	
}