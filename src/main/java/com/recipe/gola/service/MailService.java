package com.recipe.gola.service;

import java.io.IOException;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.recipe.gola.common.mail.MailHandler;
import com.recipe.gola.dto.MailDTO;

import lombok.Data;

@Service
@Data
public class MailService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private final JavaMailSender mailSender;
	
	public void sendMail(MailDTO dto) throws MessagingException, IOException {
		MailHandler mailHandler = new MailHandler(mailSender);
		
		mailHandler.setTo(dto.getAddress());
		mailHandler.setSubject(dto.getTitle());
		
		String htmlContent = "<div style='text-align:center;'><img src='cid:golalogo' style='margin-bottom:15px;'>" + dto.getMessage() + "</div>";
		mailHandler.setText(htmlContent, true);
		mailHandler.setInline("golalogo", "static/images/golalogo.png");
		mailHandler.send();
	}
}
