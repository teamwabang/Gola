package com.recipe.gola.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.recipe.gola.dto.CartDTO;
import com.recipe.gola.dto.ProductDTO;
import com.recipe.gola.dto.UserDTO;
import com.recipe.gola.service.CartService;
import com.recipe.gola.service.RecipeLinkService;
import com.recipe.gola.service.ShopService;

import lombok.Data;

@Controller
@Data
@RequestMapping("shop")
public class CartController {
	
	@Value("${file.upload.directory}")
	private String fileUploadPath;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private final ShopService ShopService;
	
	@Autowired
	private final CartService cartService;
	
	@Autowired
	private final RecipeLinkService recipelinkService;
	
	// 장바구니
	@GetMapping("cart")
	public String shop_cart() {
		logger.info("-----> 장바구니 페이지로 이동합니다.");
		return "shop/cart";
	}
	
	// 장바구니에 물건 담기
	@GetMapping("cart/add")
	public String cart_insert(@ModelAttribute CartDTO cartdto, UserDTO userdto, HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		userdto.setUserId(userId);
		 
		// 장바구니에 기존 상품이 있는지 검사
		int count = cartService.countCart(cartdto.getCNo(), userId);
		count == 0 ? cartService.updateCart(cartdto) : cartService.insert(cartdto);
		if(count == 0) {
			// 장바구니에 없으면
			cartService.insert(cartdto);
		} else {
			// 장바구니에 있으면
			cartService.updateCart(cartdto);
		}
		return "redirect:/shop/cart";
	}


}