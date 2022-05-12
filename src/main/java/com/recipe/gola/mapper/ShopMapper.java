package com.recipe.gola.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.recipe.gola.dto.ProductDTO;

@Mapper
public interface ShopMapper {

	public List<ProductDTO> shoplist(ProductDTO dto);

	public ProductDTO detail(ProductDTO dto);
}
