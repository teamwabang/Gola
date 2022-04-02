package com.recipe.gola.dto;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserDTO implements UserDetails {

	@NotBlank(message = "아이디를 입력해주세요.")
	@Pattern(regexp = "[a-zA-Z0-9]{2,9}",
    message = "아이디는 영문, 숫자로만 이루어진 2~10자리로 입력해주세요.")
	private String userId;
	
	@NotBlank(message = "비밀번호를 입력해주세요.")
	@Pattern(regexp = "^(?=.*?[a-zA-Z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
	message = "비밀번호는 영문 대/소문자, 숫자, 특수문자가 모두 1개이상 포함된 8자리 이상으로 입력해주세요.")
	private String userPwd;
	
	@NotBlank(message = "닉네임을 입력해주세요.")
	@Pattern(regexp = "[ㄱ-ㅎ가-힣]{3,8}$", message = "닉네임은 특수문자를 제외한 3~10자리로 입력해주세요.")
	private String userNickname;
	
	@NotBlank(message = "이메일을 입력해주세요.")
	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "올바르지 않은 이메일 형식입니다.")
	private String userEmail;
	private String userAuth;
	private LocalDateTime regdate;
	
	// 사용자의 권한을 콜렉션 형태로 변환
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> roles = new HashSet<>();
		for(String role : userAuth.split(",")) {
			roles.add(new SimpleGrantedAuthority(role));
		}
		return roles;
	}
	
	// 사용자의 password를 반환
	@Override
	public String getPassword() {
		return userPwd;
	}
	
	// 사용자의 id를 반환
	@Override
	public String getUsername() {
		return userId;
	}
	
	// 계정 만료 여부 반환
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	// 계정 잠금 여부 반환
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	// 패스워드 만료 여부 반환
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	// 계정 사용 가능 여부 반환
	@Override
	public boolean isEnabled() {
		return true;
	}
}
