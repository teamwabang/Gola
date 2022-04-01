package com.recipe.gola.common.code;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("/error")
	public String error(HttpServletRequest request, Model model) {
		Object status = request.getAttribute("javax.servlet.error.status_code");
		
		model.addAttribute("status", "상태 : " + status);
		model.addAttribute("paht", request.getAttribute("javax.servlet.error.request_uri"));
		model.addAttribute("timestamp", new Date());
		
		Object exceptionObj = request.getAttribute("javax.servlet.error.exception");
		if(exceptionObj != null) {
			Throwable e = ((Exception) exceptionObj).getCause();
			model.addAttribute("exception", e.getClass().getName());
			model.addAttribute("message", e.getMessage());
		}
		
		if(status.equals(HttpStatus.NOT_FOUND.value())) {
			logger.error("404 에러");
			return "error/404";
		} else if(status.equals(405)) {
			logger.error("405 에러");
			return "error/405";
		} else {
			logger.error("500 에러");
			return "error/500";
		}
	}
}
