package com.recipe.gola.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.recipe.gola.dto.RecipeLinkDTO;

@Mapper
public interface RecipeLinkMapper {

	public RecipeLinkDTO detailLink(RecipeLinkDTO linkdto);
}
