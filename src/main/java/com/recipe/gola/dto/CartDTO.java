package com.recipe.gola.dto;

import lombok.Data;

@Data
public class CartDTO {

	private int cNo;	// 장바구니 번호
	private String cUserId;	// 회원 아이디
	private int pNo;	// 상품 번호
	private int cQuantity;	// 상품 수량
}
