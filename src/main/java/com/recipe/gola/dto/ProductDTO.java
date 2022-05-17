package com.recipe.gola.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Data;

@Data
public class ProductDTO {

	private int pno;	// 상품번호
	private String pName;	// 상품명
	private String pKind;	// 상품종류
	private int pPrice;	// 상품가격
	private int pPoints;	// 적립액 (pPrice * 0.005%)
	private String pContent;	// 상품내용 *
	private String pImage;	// 상품이미지
	private String pRegdate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));	// 상품 등록날짜
}