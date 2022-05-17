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
	private final ShopService ShopService;
	
	@Autowired
	private final CartService cartService;
	
	@Autowired
	private final UserMapper userMapper;
	
	@Autowired
	private final RecipeLinkService recipelinkService;
	
	// 장바구니
	@GetMapping("cart")
	public void shop_cart(@AuthenticationPrincipal PrincipalDetails principaldetail, Model model) {
		logger.info("-----> 장바구니 페이지로 이동합니다.");
    	
		String userId = principaldetail.getUsername();
		
		List<CartDTO> list = cartService.list(userId);
		model.addAttribute("cartlist", list);
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
    	
    	if(userId == null) {
    		out.println("<script>alert('비회원은 장바구니에 담을 수 없습니다. 로그인 해주세요.'); location.href='/shop';</script>");
            out.flush();
    		return "redirect:/shop";
    	} else {
    		cartService.insert(cartdto);    		
    		return "redirect:/shop/cart";
    	}
    	
	}

}