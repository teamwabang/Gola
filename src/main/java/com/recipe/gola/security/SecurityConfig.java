package com.recipe.gola.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/", "/login", "/join", "/list").permitAll()	// 누구나 접근 허용
				.antMatchers("/user").hasRole("USER")	// USER, ADMIN만 접근 가능
				.antMatchers("/admin").hasRole("ADMIN")	// ADMIN만 접근 가능
				.anyRequest().authenticated()	// 나머지 요청들을 권한의 종류에 상관 없이 권한이 있어야 접근 가능
			.and()
				.formLogin()
					.loginPage("/login")	// 로그인 페이지 링크
					.defaultSuccessUrl("/")	// 로그인 성공 후 리다이렉트 주소
			.and()
				.logout()
					.logoutSuccessUrl("/login")	// 로그아웃 성공시 리다이렉트 주소
					.invalidateHttpSession(true)	// 세션 날리기
			.and()
				.exceptionHandling()	// 에러 처리
				.accessDeniedPage("/error")	// 에러시 이동할 페이지
		;
	}
	
	// 정적 자원에 대해서는 Security 설정을 적용하지 않음
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/css/**", "/js/**", "/images/**");
	}
	
}