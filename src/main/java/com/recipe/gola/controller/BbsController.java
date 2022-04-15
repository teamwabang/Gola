package com.recipe.gola.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import com.recipe.gola.dto.*;
import com.recipe.gola.service.BbsService;

import java.io.PrintWriter;

import javax.servlet.http.*;

@Controller
@RequestMapping("/bbs")
public class BbsController {

	private static final Logger log = LoggerFactory.getLogger(BbsController.class);
	
	@Autowired
    BbsService bbsService;
    
    //게시판 목록 페이지 이동
    @GetMapping("/list")
    public String write(Model m, BbsDTO bbsDto) throws Exception {
    	log.info("Controller @GetMapping(/list) 게시판 목록 화면이동 >>>>>>>>>>>>>>> ");
    	m.addAttribute("list", bbsService.selectListBbs(bbsDto));
        return "bbs/list";
    }

    //게시판 쓰기/수정 페이지 이동
    @GetMapping("/write")
    public String write(BbsDTO bbsDto, Model m, HttpServletResponse response, HttpSession session) throws Exception {
    	log.info("Controller @GetMapping(/write) 게시판쓰기/수정 화면이동 >>>>>>>>>>>>>>> ");
    	//String writer = (String)session.getAttribute("id");
    	
    	if(bbsDto.getBno() != null) {
        	log.info("Controller @GetMapping(/write) 화면에서 넘어온 Dto의 값 : "+bbsDto.toString());
    		m.addAttribute("bbs", bbsService.selectDetailBbs(bbsDto));
    	}
    	
    	return "bbs/write";
    }

    //게시판 상세조회 이동
    @GetMapping("/detail")
    public String detail(Model m, BbsDTO bbsDto, HttpServletResponse response, HttpSession session) throws Exception {
    	log.info("Controller @GetMapping(/detail) 게시판상세화면 이동 >>>>>>>>>>>>>>> ");
    	//String writer = (String)session.getAttribute("id");
    	
    	m.addAttribute("bbs",bbsService.selectDetailBbs(bbsDto));
    	
    	return "bbs/detail";
    }
    
    //게시판 저장 (insert문 호출)
    @PostMapping("/insertBbs")
    public void insertBbs(BbsDTO bbsDto, HttpServletResponse response, HttpSession session) {
    	
    	log.info("Controller @PostMapping(/insertBbs) 화면에서 넘어온 Dto의 값 : "+bbsDto.toString());
    	
    	//String writer = (String)session.getAttribute("id");
		response.setContentType("text/html; charset=euc-kr");
		PrintWriter out;
		int result;
		try {
			out = response.getWriter();
			result = bbsService.insertBbs(bbsDto);
			//마이바티스에서 insert문이 성공하면 숫자 1을 반환합니다.
			if(result > 0) {
				//컨트롤러에서 javascript dom을 생성하여 alert메세지를 호출후 location.href함수로 페이지를 이동합니다.
				out.println("<script>alert('게시글을 작성하였습니다.');  location.href='/bbs/list';</script>");
				out.flush();					
			}else {
				out.println("<script>alert('작성에 실패하였습니다.');  location.href='/bbs/write';</script>");
				out.flush();								
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    //게시판 삭제 (delete문 호출)
    @PostMapping("/deleteBbs")
    public void deleteBbs(BbsDTO bbsDto, HttpServletResponse response, HttpSession session) {
    	
    	log.info("Controller @PostMapping(/deleteBbs) 화면에서 넘어온 Dto의 값 : "+bbsDto.toString());
    	
    	//String writer = (String)session.getAttribute("id");
		response.setContentType("text/html; charset=euc-kr");
		PrintWriter out;
		int result;
		try {
			out = response.getWriter();
			result = bbsService.deleteBbs(bbsDto);
			//마이바티스에서 delete문이 성공하면 숫자 1을 반환합니다.
			if(result > 0) {
				//컨트롤러에서 javascript dom을 생성하여 alert메세지를 호출후 location.href함수로 페이지를 이동합니다.
				out.println("<script>alert('게시글을 삭제하였습니다.');  location.href='/bbs/list';</script>");
				out.flush();					
			}else {
				out.println("<script>alert('삭제를 실패하였습니다.');  location.href='/bbs/detail';</script>");
				out.flush();								
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    //게시판 수정 (update문 호출)
    @PostMapping("/updateBbs")
    public void updateBbs(BbsDTO bbsDto, HttpServletResponse response, HttpSession session) {

    	log.info("Controller @PostMapping(/updateBbs) 화면에서 넘어온 Dto의 값 : "+bbsDto.toString());
    	
    	//String writer = (String)session.getAttribute("id");
		response.setContentType("text/html; charset=euc-kr");
		PrintWriter out;
		int result;
		try {
			result = bbsService.updateBbs(bbsDto);
			//마이바티스에서 update문이 성공하면 숫자 1을 반환합니다.
			if(result > 0) {
				//컨트롤러에서 javascript dom을 생성하여 alert메세지를 호출후 location.href함수로 페이지를 이동합니다.
				out = response.getWriter();
				out.println("<script>alert('게시글을 수정하였습니다.');  location.href='/bbs/list';</script>");
				out.flush();					
			}else {
				out = response.getWriter();
				out.println("<script>alert('수정에 실패하였습니다.');  location.href='/bbs/write';</script>");
				out.flush();								
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}