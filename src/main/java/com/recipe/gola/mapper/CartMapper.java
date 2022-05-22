package com.recipe.gola.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.recipe.gola.dto.CartDTO;
import com.recipe.gola.dto.ProductDTO;

@Mapper
public interface CartMapper {
	
	// 내가 담은 장바구니 목록 조회
	public List<CartDTO> list(String userId);

	//내가선택한 장바구니목록
	public List<CartDTO> list2(ProductDTO productDTO);
	
	// 장바구니에 물건 담기
	public void insert(CartDTO cartdto);

	// 장바구니에 동일한 상품이 있는지 확인
	public int countCart(int cNo, String userId);

	// 장바구니에 동일한 상품이 존재하면 수정
	public void update(CartDTO cartdto);

	// 장바구니 전체 금액
	public int sumMoney(String userId);

	// 장바구니 전체 금액
	public int sumMoney2(ProductDTO productDTO);
	
	// 장바구니 수량 수정
	public int modifyCount(CartDTO cartdto);

	// 장바구니 상품 삭제
	public int delete(CartDTO cartdto);
	
}