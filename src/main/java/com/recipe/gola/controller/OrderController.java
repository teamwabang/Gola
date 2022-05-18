package com.recipe.gola.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.recipe.gola.config.auth.PrincipalDetails;
import com.recipe.gola.dto.CartDTO;
import com.recipe.gola.service.CartService;
import com.recipe.gola.service.RecipeLinkService;
import com.recipe.gola.service.ShopService;

import lombok.Data;

@Controller
@Data
@RequestMapping("shop")
public class OrderController {
	
	@Value("${file.upload.directory}")
	private String fileUploadPath;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private final ShopService shopService;
	
	@Autowired
	private final CartService cartService;
	
	@Autowired
	private final RecipeLinkService recipelinkService;
	
	// 주문서작성/결제 
	@GetMapping("order")
	public String shop_order(@AuthenticationPrincipal PrincipalDetails principaldetail, Model model) {
		logger.info("-----> 주문서작성/결제 페이지로 이동합니다.");
		
		String userId = principaldetail.getUsername();
		
		List<CartDTO> list = cartService.list(userId);
		
		int sumMoney = cartService.sumMoney(userId);
		int fee = sumMoney >= 30000 || list.size() == 0 ? 0 : 3000;
		
		model.addAttribute("list", list);
		model.addAttribute("count", list.size());	// 장바구니 상품 유무
//		model.addAttribute("money", );	// 장바구니 개별 합계 금액
		model.addAttribute("sumMoney", sumMoney);	// 장바구니 전체 금액
		model.addAttribute("fee", fee);	// 배송료
		model.addAttribute("allSum", sumMoney + fee);	// 주문 상품 전체 금액 (상품 + 배송료)
		return "shop/order";
	}
	
	// 주문완료
	@GetMapping("order/result")
	public String shop_order_result() {
		logger.info("-----> 주문이 정상적으로 완료되었습니다.");
		return "shop/order_result";
	}

}