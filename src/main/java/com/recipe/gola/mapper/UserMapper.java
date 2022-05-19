package com.recipe.gola.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.recipe.gola.dto.UserDTO;

@Mapper
public interface UserMapper {
	
	// 관리자 회원 전체리스트 출력
	public List<UserDTO> userlist();
	
	// 회원가입
	public int insertuser(UserDTO dto);
	
	// 회원가입 이메일 인증시 권한 업데이트
	public void updateAuth(UserDTO dto);
	
	// 아이디 중복확인
	public int idCheck(String userId);
	
	// 닉네임 중복확인
	public int nicknameCheck(String userNickname);
	
	// 이메일 중복확인
	public int emailCheck(String userEmail);
	
	// 로그인
	UserDTO findByUsername(String userId);
	
	// 마이페이지 정보조회
	public UserDTO infouser(String userId);
	
	// 마이페이지 회원정보 수정(닉네임)
	public void modifyPwd(UserDTO dto);
	
	// 마이페이지 회원정보 수정(닉네임)
	public void modifyNickname(UserDTO dto);
	
	// 마이페이지 회원정보 수정(이메일)
	public void modifyEmail(UserDTO dto);

	// 회원탈퇴
	public void remove(String userId);
	
}
