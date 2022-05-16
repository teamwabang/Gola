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

	public List<ProductDTO> shoplist(ProductDTO dto) {
		return shopMapper.shoplist(dto);
	}
	
	public ProductDTO shopdetail(ProductDTO dto) {
		return shopMapper.shopdetail(dto);
	}
}
