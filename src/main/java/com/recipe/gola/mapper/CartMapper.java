package com.recipe.gola.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.recipe.gola.dto.CartDTO;

@Mapper
public interface CartMapper {
	
	public List<CartDTO> list(CartDTO dto);

	// 장바구니에 물건 담기
	public void insert(CartDTO cartdto);

	// 장바구니 상품 확인
	public int countCart(int cNo, String userId);

	// 장바구니 상품 수량 변경
	public void updateCart(CartDTO cartdto);
	
}