package com.recipe.gola.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.recipe.gola.dto.RecipeDTO;

@Mapper
public interface DietMapper {

	public List<RecipeDTO> list(RecipeDTO recipeDT);

}
