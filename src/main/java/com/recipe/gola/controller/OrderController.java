package com.recipe.gola.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.recipe.gola.config.auth.PrincipalDetails;
import com.recipe.gola.dto.CartDTO;
import com.recipe.gola.dto.OrderDTO;
import com.recipe.gola.dto.ProductDTO;
import com.recipe.gola.service.CartService;
import com.recipe.gola.service.OrderService;
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

	@Autowired
	private final OrderService orderService;
	
	// 주문서작성/결제 
	@GetMapping("order")
	public String shop_order(@AuthenticationPrincipal PrincipalDetails principaldetail, Model model, ProductDTO productDTO) {

		
		logger.info("productDTO : "+productDTO.getPnos().length);
		
		logger.info("-----> 주문서작성/결제 페이지로 이동합니다.");
		
		String userId = principaldetail.getUsername();
		
		productDTO.setUserId(userId);
		
		
		
		List<CartDTO> list = cartService.list2(productDTO);
		
		int sumMoney = cartService.sumMoney2(productDTO);
		int fee = sumMoney >= 30000 || list.size() == 0 ? 0 : 3000;
		
		model.addAttribute("dto", principaldetail.getDto());
		model.addAttribute("list", list);
		model.addAttribute("count", list.size());	// 장바구니 상품 유무
//		model.addAttribute("money", );	// 장바구니 개별 합계 금액
		model.addAttribute("sumMoney", sumMoney);	// 장바구니 전체 금액
		model.addAttribute("fee", fee);	// 배송료
		model.addAttribute("allSum", sumMoney + fee);	// 주문 상품 전체 금액 (상품 + 배송료)
		return "shop/order";
	}

	// 주문완료
	@PostMapping("order/insertOrder")
	public String insertOrder(Model model, OrderDTO orderDto, HttpServletResponse response, HttpSession session, @AuthenticationPrincipal PrincipalDetails principaldetail, RedirectAttributes re) throws Exception {

		logger.info("Controller @PostMapping(/insertOrder) 화면에서 넘어온 Dto의 값 : "+orderDto.toString());
    	
		logger.info("배열 값? : "+orderDto.getPnos().length);
		
    	//로그인 사용자 조회
    	orderDto.setOUserId(principaldetail.getUsername());
    	
    	//1-1. 주문처리번호 생성
    	final int getSelectOno = orderService.selectOno();
    	final String getOrderNo = String.format("%08d", getSelectOno);
    	
    	//1-2. dto에 값 셋팅
    	orderDto.setONo(getSelectOno);
    	orderDto.setOrderNo(getOrderNo);
    	
    	CartDTO cartDTO = new CartDTO();
    	
		//1-3. 마이바티스에서는 insert문이 성공하면 숫자 1을 반환합니다.
		if(orderService.insertOrder(orderDto) > 0) {
			cartDTO.setUserId(principaldetail.getUsername());
			//장바구니 삭제, 장바구니의 상품 개수만큼 for문을 돌아 삭제함
			for(int pno : orderDto.getPnos()) {
				cartDTO.setPno(pno);
				cartService.delete(cartDTO);
			}
			
		}
		//리데이렉트로 파라미터 보내기, 주문번호 셋팅
		re.addAttribute("orderNo",getOrderNo);
		return "redirect:/shop/order/result";
		
	}
	
	// 주문완료
	@GetMapping("order/result")
	public String shop_order_result(@RequestParam("orderNo") String orderNo, Model model) {
		logger.info("-----> 주문이 정상적으로 완료되었습니다.");
		model.addAttribute("orderNo",orderNo);
		return "shop/order_result";
	}

	// 주문완료
	@GetMapping("order/check")
	public String shop_order_check(Model model, @AuthenticationPrincipal PrincipalDetails principaldetail) {
		logger.info("-----> 주문내역.");
		OrderDTO orderDto = new OrderDTO();
		orderDto.setOUserId(principaldetail.getUsername());

		List<OrderDTO> list = orderService.list(orderDto);
		
		model.addAttribute("list",list);
		model.addAttribute("count", list.size());	// 장바구니 상품 유무

		return "shop/order_check";
	}	
}