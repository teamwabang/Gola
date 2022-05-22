package com.recipe.gola.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.recipe.gola.dto.ProductDTO;

@Mapper
public interface ShopMapper {

	// 쇼핑몰 전체 상품 리스트 출력
	public List<ProductDTO> shopalllist(ProductDTO dto);
	
	//	쇼핑몰 특가 상품 리스트 출력
	public List<ProductDTO> shopdiscountlist(ProductDTO dto);

	public ProductDTO shopdetail(ProductDTO dto);
}
