package com.recipe.gola.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.recipe.gola.dto.CartDTO;

@Mapper
public interface CartMapper {
	
	public List<CartDTO> list(CartDTO dto);
	
}