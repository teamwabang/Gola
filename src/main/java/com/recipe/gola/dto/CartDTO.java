package com.recipe.gola.dto;

import lombok.Data;

@Data
public class CartDTO {

	private int cno;	// 장바구니 번호
	private String userId;	// 회원 아이디
	private int pno;	// 상품 번호
	private int camount;	// 상품 수량
	private ProductDTO ProductDTO;
}