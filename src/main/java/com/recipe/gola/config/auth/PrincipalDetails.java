package com.recipe.gola.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.recipe.gola.dto.UserDTO;

import lombok.Data;

@Data
public class PrincipalDetails implements UserDetails, OAuth2User {

	@Autowired
	private UserDTO dto;
	
	@Autowired
	private Map<String, Object> attributes;
	
	public PrincipalDetails(UserDTO dto) {
		this.dto = dto;
	}
	
	public PrincipalDetails(UserDTO dto, Map<String, Object>attributes) {
		this.dto = dto;
		this.attributes = attributes;
	}
	
	// 사용자의 권한을 콜렉션 형태로 변환
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return dto.getUserAuth().toString();
			}
		});
		return collect;
	}
	
	// 사용자의 password를 반환
	@Override
	public String getPassword() {
		return dto.getUserPwd();
	}
	
	// 사용자의 id를 반환
	@Override
	public String getUsername() {
		return dto.getUserId();
	}
	
	// 사용자의 id를 반환
	public String getUserNickname() {
		return dto.getUserNickname();
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
	
	// 계정 활성화 여부 반환
	@Override
	public boolean isEnabled() {
		return true;
	}

	// oauth2
	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	@Override
	public String getName() {
		return (String)this.attributes.get("name");
	}

}
