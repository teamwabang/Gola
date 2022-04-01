package com.recipe.gola.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.recipe.gola.dto.UserDTO;
import com.recipe.gola.mapper.UserMapper;

import lombok.Data;

@Service
@Data
public class UserServiceImpl implements UserService {

	private final UserMapper userMapper;
	
	// 회원목록
	@Override
	public List<UserDTO> userlist() {
		return userMapper.userlist();
	}
	
	// 01 - 회원가입
	@Override
	public int insertuser(@Valid UserDTO dto) {
		return userMapper.insertuser(dto);
	}
	
	// 02 - 로그인
	
	
	// 03 - 회원정보 수정
	
	
	// 04 - 회원탈퇴
	
	
	//
	
	
	//
	
	
	//
	
	@Override
	public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }

        return validatorResult;
    }



}
