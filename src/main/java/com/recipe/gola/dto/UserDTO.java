package com.recipe.gola.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.recipe.gola.config.auth.UserAuth;

import lombok.Data;

@Data
public class UserDTO {
	
//	@NotBlank(message = "아이디를 입력해주세요.")
//	@Pattern(regexp = "[a-zA-Z0-9]{2,20}$")
	private String userId;	// 회원 아이디
	
//	@NotBlank(message = "비밀번호를 입력해주세요.")
//	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^*()\\-_=+\\\\\\|\\[\\]{};:\\'\",.<>\\/?]).{8,16}$")
	private String userPwd;	// 회원 비밀번호
	private String pwdConfirm;	// 비밀번호 확인
	
//	@NotBlank(message = "닉네임을 입력해주세요.")
//	@Pattern(regexp = "^[가-힣a-zA-Z0-9]{3,20}$")
	private String userNickname;	// 회원 닉네임
	
	private String userPhone;	// 회원 전화번호
//	@NotBlank(message = "이메일을 입력해주세요.")
//	@Pattern(regexp = "^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\\.[A-Za-z0-9\\-]+")
	@Email
	private String userEmail;	// 회원 이메일
	private String emailConfirm;	// 이메일 확인
	private String userZipcode;	// 우편번호
	private String userAddr1;	// 주소
	private String userAddr2;	// 상세주소
	private UserAuth userAuth;	// 권한
	private String regdate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));	// 가입날짜
	private String modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));	// 정보변경날짜
}