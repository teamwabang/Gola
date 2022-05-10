package com.recipe.gola.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recipe.gola.dto.CartDTO;
import com.recipe.gola.mapper.CartMapper;

import lombok.Data;

@Service
@Data
public class CartService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private final CartMapper cartMapper;
	
	public List<CartDTO> list(CartDTO dto){
		return cartMapper.list(dto);
	}
	
	
}
