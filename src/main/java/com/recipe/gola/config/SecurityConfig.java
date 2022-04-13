package com.recipe.gola.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.recipe.gola.config.auth.PrincipalDetailsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private PrincipalDetailsService principalService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/", "/login", "/join").permitAll()	// 누구나 접근 허용
				.antMatchers("/user/**").access("hasAuthority('USER') or hasAuthority('ADMIN')")	// USER, ADMIN만 접근 가능
				.antMatchers("/admin/**").hasAuthority("ADMIN")	// ADMIN만 접근 가능
				.anyRequest().permitAll()	// 나머지 요청들을 권한의 종류에 상관 없이 권한이 있어야 접근 가능
			.and()
				.formLogin()
					.loginPage("/login")	// 로그인 페이지 링크
					.loginProcessingUrl("/login")
					.defaultSuccessUrl("/")	// 로그인 성공 후 리다이렉트 주소
					.failureUrl("/login")
			.and()
				.logout()
					.logoutSuccessUrl("/login")	// 로그아웃 성공시 리다이렉트 주소
					.invalidateHttpSession(true)	// 세션 날리기
			.and()
				.exceptionHandling()	// 에러 처리
				.accessDeniedPage("/error")	// 에러시 이동할 페이지
			.and()
				.sessionManagement()
					.invalidSessionUrl("/")//세션이 유효하지 않을 때 이동 할 페이지
	                .maximumSessions(1)	//세션 최대 허용 수 
	                .maxSessionsPreventsLogin(false)	// false이면 중복 로그인하면 이전 로그인이 풀린다.
		;
	}
	
	// 정적 자원에 대해서는 Security 설정을 적용하지 않음
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/css/**", "/js/**", "/images/**");
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(principalService).passwordEncoder(bCryptPasswordEncoder);
	}
	
}