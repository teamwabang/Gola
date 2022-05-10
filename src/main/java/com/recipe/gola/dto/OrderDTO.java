package com.recipe.gola.dto;

import lombok.Data;

@Data
public class OrderDTO {

	private int oNo;	// 주문처리 번호
	private String orderNo;	// 주문번호
	private String oUserId;	// 회원 아이디
	private String oName;	// 수령자 이름
	private String oPhone;	// 수령자 전화번호
	private String oZipcode;	// 수령자 우편번호
	private String oAddr1;	// 수령자 주소
	private String oAddr2;	// 수령자 상세주소
	private String oRequest;	// 배송 시 요청사항
	private String oRegdate;	// 주문 날짜
}
