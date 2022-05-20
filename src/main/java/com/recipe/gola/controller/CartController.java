package com.recipe.gola.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.recipe.gola.config.auth.PrincipalDetails;
import com.recipe.gola.dto.CartDTO;
import com.recipe.gola.dto.ProductDTO;
import com.recipe.gola.mapper.UserMapper;
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
	private final ShopService shopService;
	
	@Autowired
	private final CartService cartService;
	
	@Autowired
	private final UserMapper userMapper;
	
	@Autowired
	private final RecipeLinkService recipelinkService;
	
	// 장바구니
	@GetMapping("cart")
	public String shop_cart(@AuthenticationPrincipal PrincipalDetails principaldetail, Model model) {
		logger.info("-----> 장바구니 페이지로 이동합니다.");
    	
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
		return "shop/cart";
	}
	
	// 장바구니에 물건 담기
	@PostMapping("cart/add")
	public String insert(@AuthenticationPrincipal PrincipalDetails principaldetail, @ModelAttribute CartDTO cartdto, 
			@ModelAttribute ProductDTO productdto, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=euc-kr");
    	PrintWriter out;
    	out = response.getWriter();
    	
    	String userId = principaldetail.getUsername();
    	cartdto.setUserId(userId);
    	int pno = productdto.getPno();
    	cartdto.setPno(pno);
    	
    	int count = cartService.countCart(cartdto.getPno(), userId);
    	if(count == 0) {
    		// 장바구니에 동일한 상품이 없으면
    		cartService.insert(cartdto);
    	} else {
    		// 장바구니에 동일한 상품이 있으면
    		cartService.update(cartdto);
    	}
    	return "redirect:/shop";
	}
	
	// 장바구니 수량 수정
	@PostMapping("cart/modify")
	public String modifyCount(@AuthenticationPrincipal PrincipalDetails principaldetail, CartDTO cartdto) {
		String userId = principaldetail.getUsername();
		cartdto.setUserId(userId);
		cartService.modifyCount(cartdto);
		
		return "redirect:/shop/cart";
	}

}