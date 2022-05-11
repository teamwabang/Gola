package com.recipe.gola.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.recipe.gola.dto.ProductDTO;

@Mapper
public interface ProductMapper {
	
	public List<ProductDTO> list(ProductDTO dto);
	
}