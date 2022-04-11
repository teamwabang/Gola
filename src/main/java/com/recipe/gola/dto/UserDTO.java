package com.recipe.gola.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class UserDTO {

	@NotBlank(message = "아이디를 입력해주세요.")
	@Pattern(regexp = "[a-zA-Z0-9]{2,19}")
	private String userId;
	
	@NotBlank(message = "비밀번호를 입력해주세요.")
	@Pattern(regexp = "^(?=.*?[a-zA-Z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")
	private String userPwd;
	
	@NotBlank(message = "닉네임을 입력해주세요.")
	@Pattern(regexp = "[가-힣a-zA-Z0-9]{3,19}$")
	private String userNickname;
	
	@NotBlank(message = "이메일을 입력해주세요.")
	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$")
	private String userEmail;
	private String emailConfirm;
	private UserAuth userAuth;
	private String regdate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
}