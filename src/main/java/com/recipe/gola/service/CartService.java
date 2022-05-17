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
	
	// 장바구니 리스트
	public List<CartDTO> list(String userId){
		return cartMapper.list(userId);
	}
	
	// 장바구니에 물건 담기
	public void insert(CartDTO cartdto) {
		cartMapper.insert(cartdto);
	}
	
	// 장바구니 상품 확인
	public int countCart(int cNo, String userId) {
		return cartMapper.countCart(cNo, userId);
	}
	
	// 장바구니 상품 수량 변경
	public void updateCart(CartDTO cartdto) {
		cartMapper.updateCart(cartdto);
	}
	
}
