package com.recipe.gola.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.recipe.gola.common.validate.Validate;
import com.recipe.gola.config.auth.PrincipalDetails;
import com.recipe.gola.config.auth.UserAuth;
import com.recipe.gola.dto.BbsDTO;
import com.recipe.gola.dto.FilesDTO;
import com.recipe.gola.dto.UserDTO;
import com.recipe.gola.service.BbsService;
import com.recipe.gola.service.UserService;

import lombok.Data;

@Controller
@Data
public class UserController {
	
	@Value("${file.upload.directory}")
	private String fileUploadPath;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private final UserService userService;

	@Autowired
	private final BbsService bbsService;
	
	@Autowired
	private final Validate validate;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	// 회원가입
	@GetMapping("join")
	public String userjoin() {
		logger.info("-----> 회원가입을 시도 중 입니다.");
		return "index";
	}
	
	@PostMapping("join")
	public void userjoin(@Valid UserDTO dto, String userId, String userEmail, String userNickname, 
			Errors errors, Model model, HttpServletResponse response) throws Exception {
//        if (errors.hasErrors()) {
//            // 회원가입 실패시, 입력 데이터를 유지
//            model.addAttribute("insertuser", dto);
//
//            // 유효성 통과 못한 필드와 메시지를 핸들링
//            Map<String, String> validatorResult = validate.validateHandling(errors);
//            for (String key : validatorResult.keySet()) {
//                model.addAttribute(key, validatorResult.get(key));
//            }
//
//            logger.error("=====> 회원가입에 실패하였습니다.");
//            return "redirect:/";
//        }
		response.setContentType("text/html; charset=euc-kr");
        PrintWriter out;
        out = response.getWriter();
        
		int result = userService.idCheck(userId);
		int result2 = userService.nicknameCheck(userNickname);
		int result3 = userService.emailCheck(userEmail);
		
		try {
			if(result == 1 || result2 == 1 || result3 == 1) {
				logger.error("=====> 회원가입에 실패하였습니다.");
				model.addAttribute("result", "fail");
				
				out.println("<script>alert('회원가입에 실패하였습니다.'); location.href='/';</script>");
		        out.flush();
			} else if(result == 0 || result2 == 0 || result3 == 0) {
				logger.info("-----> 회원가입에 성공하였습니다.");
				String rawPwd = dto.getUserPwd();
				String encPwd = bCryptPasswordEncoder.encode(rawPwd);
				dto.setUserPwd(encPwd);
				userService.insertuser(dto);
				
				out.println("<script>alert('회원가입에 성공하였습니다.'); location.href='/';</script>");
		        out.flush();
			}			
		} catch(Exception e) {
			throw new RuntimeException();
		}
    }
	
	// 아이디 중복확인
	@PostMapping("/idCheck")
	@ResponseBody
	public int idCheck(String userId) {
		int result = userService.idCheck(userId);
		return result;
	}
	
	// 닉네임 중복확인
	@PostMapping("/nicknameCheck")
	@ResponseBody
	public int nicknameCheck(String userNickname) {
		int result = userService.nicknameCheck(userNickname);
		return result;
	}
	
	// 이메일 중복확인
	@PostMapping("/emailCheck")
	@ResponseBody
	public int emailCheck(String userEmail) {
		int result = userService.emailCheck(userEmail);
		return result;
	}
	
	// 로그인
	@GetMapping("login")
	public String login() {
		return "index";
	}
	
	// 로그아웃
	@GetMapping("logout")
	public ModelAndView logout(HttpSession session) {
		Object object = session.getAttribute("login");
		
		if(object != null) {
			session.removeAttribute("login");
			session.invalidate();
		}
		return new ModelAndView("redirect:/");
	}
	
	// 마이페이지
	@GetMapping("mypage")
	public String mypage(@AuthenticationPrincipal PrincipalDetails principaldetail, Model model) throws Exception {
		logger.info("-----> 마이페이지로 이동합니다.");
		logger.info("유저 아이디 : " + principaldetail.getUsername());
		
		model.addAttribute("dto", principaldetail.getDto());
	    BbsDTO bbsDto = new BbsDTO();
	    bbsDto.setWriter(principaldetail.getUsername());
		model.addAttribute("list",bbsService.selectListBbs(bbsDto));		
		String imgSrc = bbsService.selectListFiles(principaldetail.getUsername()).get(0).getImgSrc();
		String fno =  bbsService.selectListFiles(principaldetail.getUsername()).get(0).getFno();
		String fileName = bbsService.selectListFiles(principaldetail.getUsername()).get(0).getFileName();
		
		//file Info
		model.addAttribute("imgSrc", imgSrc);
		model.addAttribute("fno",fno);
		model.addAttribute("fileName",fileName);
		
		
		return "user/mypage";
	}
	
	// 마이페이지 회원정보 수정(비밀번호)
	@PostMapping("mypage/modify/password")
	public void modifyPwd(@AuthenticationPrincipal PrincipalDetails principaldetail, 
			UserDTO dto, HttpSession session, HttpServletResponse response) throws Exception {
		logger.info("-----> 회원 " + principaldetail.getUsername() + "님이 비밀번호를 수정하였습니다.");
		
		dto.setUserAuth(UserAuth.USER);
        
		String rawPwd = dto.getUserPwd();
		String encPwd = bCryptPasswordEncoder.encode(rawPwd);
		dto.setUserPwd(encPwd);
        userService.modifyPwd(dto);

        session.invalidate();
        
        response.setContentType("text/html; charset=euc-kr");
        PrintWriter out;
        out = response.getWriter();
        out.println("<script>alert('회원정보를 수정하여 재로그인이 필요합니다.'); location.href='/';</script>");
        out.flush();
	}

	// 마이페이지 회원정보 수정(닉네임)
	@PostMapping("mypage/modify/nickname")
	public void modifyNickname(@AuthenticationPrincipal PrincipalDetails principaldetail, 
			UserDTO dto, HttpSession session, HttpServletResponse response) throws Exception {
		logger.info("-----> 회원 " + principaldetail.getUsername() + "님이 닉네임을 수정하였습니다.");
		
		dto.setUserAuth(UserAuth.USER);
        
        userService.modifyNickname(dto);

        session.invalidate();
		
        response.setContentType("text/html; charset=euc-kr");
        PrintWriter out;
        out = response.getWriter();
        out.println("<script>alert('회원정보를 수정하여 재로그인이 필요합니다.'); location.href='/';</script>");
        out.flush();
	}
	
	// 마이페이지 회원정보 수정(이메일)
		@PostMapping("mypage/modify/email")
		public void modifyEmail(@AuthenticationPrincipal PrincipalDetails principaldetail, 
				UserDTO dto, HttpSession session, HttpServletResponse response) throws Exception {
			logger.info("-----> 회원 " + principaldetail.getUsername() + "님이 이메일을 수정하였습니다.");
			
			dto.setUserAuth(UserAuth.USER);
	        
	        userService.modifyEmail(dto);

	        session.invalidate();
			
	        response.setContentType("text/html; charset=euc-kr");
	        PrintWriter out;
	        out = response.getWriter();
	        out.println("<script>alert('회원정보를 수정하여 재로그인이 필요합니다.'); location.href='/';</script>");
	        out.flush();

		}

	
	// 회원탈퇴
	@GetMapping("mypage/leave")
	public String remove(@AuthenticationPrincipal PrincipalDetails principaldetail, 
			Model model, HttpServletResponse response) {
		logger.info("-----> 회원탈퇴 페이지로 이동합니다.");
		logger.info("유저 아이디 : " + principaldetail.getUsername());
		model.addAttribute("dto", principaldetail.getDto());
		return "user/leave";
	}

	
    @PostMapping("mypage/insertProfilePhoto")
    public void insertProfilePhoto(MultipartFile upFile, HttpServletResponse response, HttpSession session, @AuthenticationPrincipal PrincipalDetails principaldetail) throws Exception {
    	
    	response.setContentType("text/html; charset=euc-kr");
    	PrintWriter out;
    	out = response.getWriter();
    	
    	logger.info("Controller @PostMapping(/insertProfilePhoto) 화면에서 넘어온 Dto의 값 : "+upFile);
    	
    	//로그인 사용자 조회   	
     	String writer = principaldetail.getUsername();
    
		int result;

		try {
				//html에서 넘어온 input=file 객체가 있으면
	    	if(!upFile.isEmpty()) {
	    		logger.info("파일이 있네...");
	    		
	    		//2-1. 업로드 폴더가 없으면 생성
	    		File dir = new File(fileUploadPath);
	    		if (dir.exists() == false) {
	    			dir.mkdirs();
	    		}

				/* 파일 확장자 */
				final String extension = FilenameUtils.getExtension(upFile.getOriginalFilename());
				
				/* 서버에 저장할 파일명 (랜덤 문자열 + 확장자) */
				final String saveName = getRandomString() + "." + extension;
				final String fileName = upFile.getOriginalFilename();
				final long fileSize = upFile.getSize();

				logger.info("::::: 원본 파일이름 file.getOriginalFilename() >>>>> "+upFile.getOriginalFilename());
				logger.info("::::: 파일 사이즈 file.size() >>>>> "+upFile.getSize());
				logger.info("::::: 저장 파일이름 saveName >>>>> "+saveName);			
				
				//2-3.파일정보 데이터베이스에 저장
				FilesDTO filesDto = new FilesDTO();
				filesDto.setBno(writer);				//user Id
				filesDto.setFileName(fileName);			//파일원본이름
				filesDto.setFileSavename(saveName);		//파일저장이름
				filesDto.setFileSize(Long.toString(fileSize));		//파일사이즈
				filesDto.setFilePath(fileUploadPath+"/");				//파일업로드한 경로
				
				result = bbsService.insertFiles(filesDto);

				//2-4. 파일저장이 성공시 메세지 출력
				if(result >0) {

					//2-5. 업로드 경로에 saveName과 동일한 이름을 가진 파일 생성 
					File target = new File(fileUploadPath, saveName);
					upFile.transferTo(target);
					
					//컨트롤러에서 javascript dom을 생성하여 alert메세지를 호출후 location.href함수로 페이지를 이동합니다.
					out.println("<script>alert('프로필 사진을 등록하였습니다.');  location.href='/mypage';</script>");
					out.flush();		
				}else {
					out.println("<script>alert('프로필 사진 등록에 실패하였습니다.');  location.href='/mypage';</script>");
					out.flush();					    					
				}
	    	}
			
		} catch (Exception e) {
			out.println("<script>alert('프로필 사진 등록에 실패하였습니다.');  location.href='/mypage';</script>");
			out.flush();	
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
		
    }

    @PostMapping("mypage/updateProfilePhoto")
    public void updateProfilePhoto(@RequestParam Map<String,Object> paramMap, MultipartFile upFile, HttpServletResponse response, HttpSession session, @AuthenticationPrincipal PrincipalDetails principaldetail) throws Exception {
    	
    	response.setContentType("text/html; charset=euc-kr");
    	PrintWriter out;
    	out = response.getWriter();
    	
    	logger.info("Controller @PostMapping(/insertProfilePhoto) 화면에서 넘어온 Dto의 값 : "+upFile);
    	logger.info("param Values>>>>>>>>>>>>>>>>> "+paramMap.toString());
    	
    	String fno = "";
    	if(!paramMap.isEmpty()){
    		fno = paramMap.get("deleteFilesNo").toString();
    	}
    	//로그인 사용자 조회   	
     	String writer = principaldetail.getUsername();
    
		int result;

		try {
				//html에서 넘어온 input=file 객체가 있으면
	    	if(!upFile.isEmpty()) {
	    		logger.info("파일이 있네...");
	    		
	    		//2-1. 업로드 폴더가 없으면 생성
	    		File dir = new File(fileUploadPath);
	    		if (dir.exists() == false) {
	    			dir.mkdirs();
	    		}

				/* 파일 확장자 */
				final String extension = FilenameUtils.getExtension(upFile.getOriginalFilename());
				
				/* 서버에 저장할 파일명 (랜덤 문자열 + 확장자) */
				final String saveName = getRandomString() + "." + extension;
				final String fileName = upFile.getOriginalFilename();
				final long fileSize = upFile.getSize();

				logger.info("::::: 원본 파일이름 file.getOriginalFilename() >>>>> "+upFile.getOriginalFilename());
				logger.info("::::: 파일 사이즈 file.size() >>>>> "+upFile.getSize());
				logger.info("::::: 저장 파일이름 saveName >>>>> "+saveName);			
				
				//2-3.파일정보 데이터베이스에 저장
				FilesDTO filesDto = new FilesDTO();
				filesDto.setBno(writer);				//user Id
				filesDto.setFileName(fileName);			//파일원본이름
				filesDto.setFileSavename(saveName);		//파일저장이름
				filesDto.setFileSize(Long.toString(fileSize));		//파일사이즈
				filesDto.setFilePath(fileUploadPath+"/");				//파일업로드한 경로
				
				result = bbsService.insertFiles(filesDto);

				//2-4. 파일저장이 성공시 메세지 출력
				if(result >0) {

					//2-5. 업로드 경로에 saveName과 동일한 이름을 가진 파일 생성 
					File target = new File(fileUploadPath, saveName);
					upFile.transferTo(target);	
					
					if(!"".equals(fno)) {
	    				logger.info("::::: 삭제 파일번호 fno >>>>> "+fno);			
	    				
						FilesDTO deleteFileDto = new FilesDTO();
	
						deleteFileDto = bbsService.selectDetailFiles(fno);
						
						final String fileSavename = deleteFileDto.getFileSavename();
	
	    				logger.info("::::: 삭제 파일경로 >>>>> "+fileUploadPath+"/"+fileSavename);			
						
						File deleteTarget = new File(fileUploadPath,fileSavename); 
						if(deleteTarget.exists()) { // 파일이 존재하면 
							deleteTarget.delete(); // 파일 삭제 
						}
						//2-7 기존파일정보 데이터베이스에서 삭제
						result = bbsService.deleteFiles(fno);
						logger.info("::::::::: 파일정보 삭제결과 result >>>>>> " +result);
					
					}
					
					//컨트롤러에서 javascript dom을 생성하여 alert메세지를 호출후 location.href함수로 페이지를 이동합니다.
					out.println("<script>alert('프로필 사진을 등록하였습니다.');  location.href='/mypage';</script>");
					out.flush();		
				}else {
					out.println("<script>alert('프로필 사진 등록에 실패하였습니다.');  location.href='/mypage';</script>");
					out.flush();					    					
				}
	    	}
			
		} catch (Exception e) {
			out.println("<script>alert('프로필 사진 등록에 실패하였습니다.');  location.href='/mypage';</script>");
			out.flush();	
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
		
		
		
    }    
    
	
	@PostMapping("mypage/leave")
	public void remove(@AuthenticationPrincipal PrincipalDetails principaldetail, 
			@RequestParam String userId, Model model, HttpSession session, HttpServletResponse response) throws Exception {
		logger.info("-----> 회원탈퇴가 정상적으로 완료되었습니다.");
		
//		Cookie cookie = new Cookie("remember-me", null);
//		cookie.setMaxAge(0);
//		response.addCookie(cookie);
		
		userService.remove(userId);
		SecurityContextHolder.clearContext();
		session.invalidate();
		
		response.setContentType("text/html; charset=euc-kr");
        PrintWriter out;
        out = response.getWriter();
        out.println("<script>alert('정상적으로 회원탈퇴 되었습니다. 감사합니다'); location.href='/';</script>");
        out.flush();
	}
	
    //파일생성을 위한 랜덤난수값 생성
	private final String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
}