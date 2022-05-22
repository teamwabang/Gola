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
import org.springframework.web.bind.annotation.RequestMapping;

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
	@GetMapping("order/insertOrder")
	public void insertOrder(OrderDTO orderDto, HttpServletResponse response, HttpSession session, @AuthenticationPrincipal PrincipalDetails principaldetail) throws Exception {
		response.setContentType("text/html; charset=euc-kr");
    	PrintWriter out;
    	out = response.getWriter();

    	logger.info("Controller @PostMapping(/insertOrder) 화면에서 넘어온 Dto의 값 : "+orderDto.toString());
    	
    	//로그인 사용자 조회
    	orderDto.setOUserId(principaldetail.getUsername());
    	
    	//1-1. 게시글번호 생성 (※게시판저장 및 파일저장의 사용)
    	final String getSelectOno = orderService.selectOno();
    	//1-2. bbsDto에 생성한 게시글번호를 넣는다.
    	orderDto.setONo(Integer.parseInt(getSelectOno));

		try {
			//1-3. 마이바티스에서는 insert문이 성공하면 숫자 1을 반환합니다.
			if(orderService.insertOrder(orderDto) > 0) {
				out.println("<script>alert('결제를 완료 하였습니다.');  location.href='/order/result';</script>");
				out.flush();	
			}else {
				out.println("<script>alert('작성에 실패하였습니다.');  location.href='/';</script>");
				out.flush();	
			}
		} catch (Exception e) {
			out.println("<script>alert('작성에 실패하였습니다.');  location.href='/';</script>");
			out.flush();	
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	// 주문완료
	@GetMapping("order/result")
	public String shop_order_result() {
		logger.info("-----> 주문이 정상적으로 완료되었습니다.");
		return "shop/order_result";
	}

}