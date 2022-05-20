package com.recipe.gola.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recipe.gola.dto.CartDTO;
import com.recipe.gola.mapper.CartMapper;

import lombok.Data;

@Service
@Data
@Transactional
public class CartService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	SqlSession session;
	
	@Autowired
	private final CartMapper cartMapper;
	
	// 내가 담은 장바구니 목록 조회
	public List<CartDTO> list(String userId){
		return cartMapper.list(userId);
	}
	
	// 장바구니에 물건 담기
	public void insert(CartDTO cartdto) {
		cartMapper.insert(cartdto);
	}
	
	// 장바구니에 동일한 상품이 있는지 확인
	public int countCart(int pno, String userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pno", pno);
		map.put("userId", userId);
		return session.selectOne("countCart", map);
	}
	
	// 장바구니에 동일한 상품이 존재하면 수정
	public void update(CartDTO cartdto) {
		cartMapper.update(cartdto);
	}

	// 장바구니 전체 금액
	public int sumMoney(String userId) {
		return cartMapper.sumMoney(userId);
	}
	
	// 장바구니 수량 수정
	public int modifyCount(CartDTO cartdto) {
		return cartMapper.modifyCount(cartdto);
	}
	
}
