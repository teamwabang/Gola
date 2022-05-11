package com.recipe.gola.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recipe.gola.dto.ProductDTO;
import com.recipe.gola.mapper.ProductMapper;

import lombok.Data;

@Service
@Data
public class ProductService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private final ProductMapper productMapper;
	
	public List<ProductDTO> list(ProductDTO dto){
		return productMapper.list(dto);
	}
	
	
}
