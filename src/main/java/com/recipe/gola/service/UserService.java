package com.recipe.gola.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.validation.Errors;

import com.recipe.gola.dto.UserDTO;

public interface UserService {

	// 회원목록
	public List<UserDTO> userlist();

	public int insertuser(@Valid UserDTO dto);
	
	Map<String, String> validateHandling(Errors errors);

	
}
