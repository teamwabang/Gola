package com.recipe.gola.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class UserDTO {

	@NotBlank(message = "아이디를 입력해주세요.")
	@Pattern(regexp = "[a-zA-Z0-9]{2,9}",
    message = "아이디는 영문, 숫자로만 이루어진 2~10자리로 입력해주세요.")
	private String userId;
	
	@NotBlank(message = "비밀번호를 입력해주세요.")
	@Pattern(regexp = "^(?=.*?[a-zA-Z])(?=.*?[0-9])(?=.*?[~.!@#$%^&*()_-+={}[]|\\\\;:'\\\"<>,.?/\"\n]).{8,}$",
	message = "비밀번호는 영문 대/소문자, 숫자, 특수문자가 모두 1개이상 포함된 8자리 이상으로 입력해주세요.")
	private String userPwd;
	
	@NotBlank(message = "이름을 입력해주세요.")
	@Pattern(regexp = "[ㄱ-ㅎ가-힣a-z]{3,8}$", message = "이름은 특수문자를 제외한 3~10자리로 입력해주세요.")
	private String userName;
	
	private String userBirth;
	
	@NotBlank(message = "숫자만 입력해주세요.")
    @Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})", message = "올바른 휴대폰 번호를 입력해주세요.")
	private String userPhone;
	
	@NotBlank(message = "이메일을 입력해주세요.")
	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "올바르지 않은 이메일 형식입니다.")
	private String userEmail;
	private String userAddress;
	private LocalDateTime regdate;
}
