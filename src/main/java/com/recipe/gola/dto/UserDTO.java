package com.recipe.gola.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class UserDTO {
	
//	@NotBlank(message = "아이디를 입력해주세요.")
//	@Pattern(regexp = "[a-zA-Z0-9]{2,20}$")
	private String userId;
	
//	@NotBlank(message = "비밀번호를 입력해주세요.")
//	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^*()\\-_=+\\\\\\|\\[\\]{};:\\'\",.<>\\/?]).{8,16}$")
	private String userPwd;
	private String pwdConfirm;
	
//	@NotBlank(message = "닉네임을 입력해주세요.")
//	@Pattern(regexp = "^[가-힣a-zA-Z0-9]{3,20}$")
	private String userNickname;
	
//	@NotBlank(message = "이메일을 입력해주세요.")
//	@Pattern(regexp = "^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\\.[A-Za-z0-9\\-]+")
	@Email
	private String userEmail;
	
	private String emailConfirm;
	private UserAuth userAuth;
	private String regdate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	private String modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
}