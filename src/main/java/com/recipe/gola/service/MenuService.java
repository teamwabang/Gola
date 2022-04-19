package com.recipe.gola.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recipe.gola.dto.MenuDTO;
import com.recipe.gola.mapper.MenuMapper;

import lombok.Data;

@Service
@Data
public class MenuService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private final MenuMapper menuMapper;

	public List<MenuDTO> menulist() {
		return menuMapper.menulist();
	}
	
	public MenuDTO detail(int no) {
		return menuMapper.detail(no);
	}
}
