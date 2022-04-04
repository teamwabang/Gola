package com.recipe.gola.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class UserDTO {

	@NotBlank(message = "아이디를 입력해주세요.")
	@Pattern(regexp = "[a-zA-Z0-9]{2,19}",
    message = "아이디는 영문, 숫자로만 이루어진 2~20자리로 입력해주세요.")
	private String userId;
	
	@NotBlank(message = "비밀번호를 입력해주세요.")
	@Pattern(regexp = "^(?=.*?[a-zA-Z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
	message = "비밀번호는 영문 대/소문자, 숫자, 특수문자가 모두 1개이상 포함된 8자리 이상으로 입력해주세요.")
	private String userPwd;
	
	@NotBlank(message = "닉네임을 입력해주세요.")
	@Pattern(regexp = "[가-힣a-zA-Z0-9]{3,19}$", message = "닉네임은 특수문자를 제외한 3~20자리로 입력해주세요.")
	private String userNickname;
	
	@NotBlank(message = "이메일을 입력해주세요.")
	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "올바르지 않은 이메일 형식입니다.")
	private String userEmail;
	private UserAuth userAuth;
	private String regdate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
}