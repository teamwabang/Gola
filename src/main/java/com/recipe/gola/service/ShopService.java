package com.recipe.gola.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recipe.gola.dto.ProductDTO;
import com.recipe.gola.mapper.ShopMapper;

import lombok.Data;

@Service
@Data
public class ShopService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private final ShopMapper shopMapper;

	// 쇼핑몰 전체 상품 리스트 출력
	public List<ProductDTO> shopalllist(ProductDTO dto) {
		return shopMapper.shopalllist(dto);
	}
	
	//	쇼핑몰 특가 상품 리스트 출력
	public List<ProductDTO> shopdiscountlist(ProductDTO dto) {
		return shopMapper.shopdiscountlist(dto);
	}
	
	public ProductDTO shopdetail(ProductDTO dto) {
		return shopMapper.shopdetail(dto);
	}
}
