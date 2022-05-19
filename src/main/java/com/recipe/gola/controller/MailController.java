package com.recipe.gola.controller;

import java.io.IOException;
import java.util.Random;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recipe.gola.dto.MailDTO;
import com.recipe.gola.service.MailService;

import lombok.Data;

@RestController
@Data
public class MailController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private final MailService mailService;
	
	@GetMapping("mail")
	public MailDTO mail(String email, Model model) throws MessagingException, IOException {
		logger.info("-----> 회원가입 이메일 인증코드를 발송하였습니다.");
		
		MailDTO maildto = new MailDTO();
		Random random = new Random();
		String key = "";
		
		for(int i = 0; i < 3; i++) {
			int index = random.nextInt(25) + 65;
			key += (char)index;
		}
		int numIndex = random.nextInt(9999) + 1000;
		key += numIndex;
		
		maildto.setAddress(email);
		maildto.setTitle("골라, 회원가입 인증 메일입니다.");
		
		String msg = "";
        msg += "<div>";
        msg += "<span style='font-size:32px;font-weight:500;'>이메일 인증코드</span><br><br>";
        msg += "<span style='font-size:16px;font-weight:400;'>우리들의 모든 레시피, 골라에 오신것을 환영합니다.<br>";
        msg += "아래의 인증코드를 입력하시면 가입을 정상적으로 완료하실 수 있습니다.</br>";
        msg += "<div align='center' style='margin:50px;'>";
        msg += "<strong style=';font-size:36px;font-weight:600;'>" + key + "</strong><div>";
        msg += "</div>";
		maildto.setMessage(msg);
		
		mailService.sendMail(maildto);
		
		return maildto;
	}

}