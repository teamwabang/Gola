package com.recipe.gola.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.recipe.gola.dto.CartDTO;

@Mapper
public interface CartMapper {
	
	// 내가 담은 장바구니 목록 조회
	public List<CartDTO> list(String userId);

	// 장바구니에 물건 담기
	public void insert(CartDTO cartdto);

	// 장바구니에 동일한 상품이 있는지 확인
	public int countCart(int cNo, String userId);

	// 장바구니에 동일한 상품이 존재하면 수정
	public void update(CartDTO cartdto);

	public int sumMoney(String userId);
	
}