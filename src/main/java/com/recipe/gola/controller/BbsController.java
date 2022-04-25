package com.recipe.gola.controller;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.recipe.gola.config.auth.PrincipalDetails;
import com.recipe.gola.dto.*;
import com.recipe.gola.service.BbsService;
import com.recipe.gola.service.CommentService;
import com.recipe.gola.service.FilesService;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.*;

@Controller
@RequestMapping("/bbs")
public class BbsController {
	
	@Value("${file.upload.directory}")
	private String fileUploadPath;

	private static final Logger log = LoggerFactory.getLogger(BbsController.class);
	
	@Autowired
    BbsService bbsService;

	@Autowired
	FilesService filesService;
	
	@Autowired
    CommentService commentService;
	
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
        	m.addAttribute("fileList",bbsService.selectListFiles(bbsDto.getBno()));
    	}
    	
    	return "bbs/write";
    }

    //게시판 상세조회 이동
    @GetMapping("/detail")
    public String detail(Model m, BbsDTO bbsDto, HttpServletResponse response, HttpSession session, @AuthenticationPrincipal PrincipalDetails principaldetail) throws Exception {
    	log.info("Controller @GetMapping(/detail) 게시판상세화면 이동 >>>>>>>>>>>>>>> ");
    	//String writer = (String)session.getAttribute("id");

    	m.addAttribute("bbs",bbsService.selectDetailBbs(bbsDto));
    	m.addAttribute("fileList",bbsService.selectListFiles(bbsDto.getBno()));
    	 
    	bbsService.updateCnt(bbsDto.getBno());
//    	if(bbsDto.getWriter().equals(principaldetail.getUsername())) {
//    		bbsService.updateCnt(bbsDto.getBno());
//    	}
    	
    	return "bbs/detail";
    }
    
    //게시판 저장 (insert문 호출)
    @PostMapping("/insertBbs")
    public void insertBbs(BbsDTO bbsDto, HttpServletResponse response, HttpSession session, @AuthenticationPrincipal PrincipalDetails principaldetail) throws Exception {
    	
    	response.setContentType("text/html; charset=euc-kr");
    	PrintWriter out;
    	out = response.getWriter();
    	log.info("Controller @PostMapping(/insertBbs) 화면에서 넘어온 Dto의 값 : "+bbsDto.toString());
    	log.info(":::::application.properties 선언한 파일업로드 경로 fileUploadPath >>>>>>>>> "+fileUploadPath);
    	
    	//로그인 사용자 조회
    	bbsDto.setWriter(principaldetail.getUsername());
    	
    	//1-1. 게시글번호 생성 (※게시판저장 및 파일저장의 사용)
    	final String getSelectBno = bbsService.selectBno();
    	//1-2. bbsDto에 생성한 게시글번호를 넣는다.
    	bbsDto.setBno(getSelectBno);

		try {
			//1-3. 마이바티스에서는 insert문이 성공하면 숫자 1을 반환합니다.
			if(bbsService.insertBbs(bbsDto) > 0) {
				
				if(filesService.fileUpload(bbsDto.getUpFile(), getSelectBno, fileUploadPath, bbsDto.getDeleteFilesNo())) {
					//컨트롤러에서 javascript dom을 생성하여 alert메세지를 호출후 location.href함수로 페이지를 이동합니다.
					out.println("<script>alert('게시글을 작성하였습니다.');  location.href='/bbs/review';</script>");
					out.flush();		
				}else {
					out.println("<script>alert('작성에 실패하였습니다.');  location.href='/bbs/review';</script>");
					out.flush();						
				}
				
			}else {
				
				out.println("<script>alert('작성에 실패하였습니다.');  location.href='/bbs/review';</script>");
				out.flush();	
				
			}
		} catch (Exception e) {
			out.println("<script>alert('작성에 실패하였습니다.');  location.href='/bbs/review';</script>");
			out.flush();	
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
		
    }

    //게시판 삭제 (delete문 호출)
    @PostMapping("/deleteBbs")
    public void deleteBbs(BbsDTO bbsDto, HttpServletResponse response, HttpSession session) throws Exception {
    	
    	log.info("Controller @PostMapping(/deleteBbs) 화면에서 넘어온 Dto의 값 : "+bbsDto.toString());
    	
    	//String writer = (String)session.getAttribute("id");
		response.setContentType("text/html; charset=euc-kr");
		PrintWriter out;
		out = response.getWriter();
		
		final String bno = bbsDto.getBno();
		
		int result;

		try {
			
			commentService.deleteAll(Integer.parseInt(bno));				
			
			result = bbsService.deleteBbs(bbsDto);
			//마이바티스에서 delete문이 성공하면 숫자 1을 반환합니다.
			if(result > 0) {
				

				//게시글번호 기준으로 전체 파일목록을 조회한다.
				List<FilesDTO> deleteListFiles= bbsService.selectListFiles(bno);
				
				for (FilesDTO filesDto : deleteListFiles) {

					final String fileSavename = filesDto.getFileSavename();
					final String fno = filesDto.getFno();

					log.info("::::: 삭제 파일경로 >>>>> "+fileUploadPath+"/"+fileSavename);			
					File deleteTarget = new File(fileUploadPath,fileSavename); 

					if(deleteTarget.exists()) { // 파일이 존재하면 
						deleteTarget.delete(); // 파일 삭제 
					}
					//기존파일정보 데이터베이스에서 삭제
					result = bbsService.deleteFiles(fno);
					log.info("::::::::: 파일정보 삭제결과 result >>>>>> " +result);
				}
				

				
				//컨트롤러에서 javascript dom을 생성하여 alert메세지를 호출후 location.href함수로 페이지를 이동합니다.
				out.println("<script>alert('게시글을 삭제하였습니다.');  location.href='/bbs/review';</script>");
				out.flush();					
			}else {
				out.println("<script>alert('삭제를 실패하였습니다.');  location.href='/bbs/review';</script>");
				out.flush();								
			}
		} catch (Exception e) {
			out.println("<script>alert('작성에 실패하였습니다.');  location.href='/bbs/review';</script>");
			out.flush();	
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    //게시판 수정 (update문 호출)
    @PostMapping("/updateBbs")
    public void updateBbs(BbsDTO bbsDto, HttpServletResponse response, HttpSession session) throws Exception {

    	log.info("Controller @PostMapping(/updateBbs) 화면에서 넘어온 Dto의 값 : "+bbsDto.toString());
    	
    	response.setContentType("text/html; charset=euc-kr");

    	PrintWriter out;
    	out = response.getWriter();
    	
    	log.info(":::::application.properties 선언한 파일업로드 경로 fileUploadPath >>>>>>>>> "+fileUploadPath);
    	
    	//1-1. DTO에서 게시글번호 추출 (※게시판수정/파일저장/파일삭제의 사용)
    	final String getSelectBno = bbsDto.getBno();

		try {

			//1-3. 마이바티스에서는 update문이 성공하면 숫자 1을 반환합니다.
			if(bbsService.updateBbs(bbsDto) > 0) {
				
				if(filesService.fileUpload(bbsDto.getUpFile(), getSelectBno, fileUploadPath, bbsDto.getDeleteFilesNo())) {
					//컨트롤러에서 javascript dom을 생성하여 alert메세지를 호출후 location.href함수로 페이지를 이동합니다.
					out.println("<script>alert('게시글을 작성하였습니다.');  location.href='/bbs/review';</script>");
					out.flush();		
				}else {
					out.println("<script>alert('게시글을 작성하였습니다.');  location.href='/bbs/review';</script>");
					out.flush();						
				}
				
			}else {
				
				out.println("<script>alert('작성에 실패하였습니다.');  location.href='/bbs/review';</script>");
				out.flush();	
				
			}
			
		} catch (Exception e) {
			out.println("<script>alert('작성에 실패하였습니다.');  location.href='/bbs/review';</script>");
			out.flush();	
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
    }

    //리뷰 목록 페이지 이동
    @GetMapping("/review")
    public String review(Model m, BbsDTO bbsDto) throws Exception {
    	log.info("Controller @GetMapping(/review) 리뷰 목록 화면이동 >>>>>>>>>>>>>>> ");
    	m.addAttribute("list", bbsService.selectListBbs(bbsDto));
        return "bbs/review";
    }

}