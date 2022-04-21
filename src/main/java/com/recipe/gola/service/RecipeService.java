package com.recipe.gola.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recipe.gola.dto.RecipeDTO;
import com.recipe.gola.mapper.RecipeMapper;

import lombok.Data;

@Service
@Data
public class RecipeService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private final RecipeMapper recipeMapper;

	public List<RecipeDTO> list() {
		return recipeMapper.list();
	}
	
	public RecipeDTO detail(RecipeDTO dto) {
		return recipeMapper.detail(dto);
	}
}
