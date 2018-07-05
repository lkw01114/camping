package org.study.board.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityController.class);
	
	@RequestMapping("/login")
	public void login(Locale locale, Model model) {
		logger.info("login into");
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );		
		
	}
	
	@RequestMapping("/error")
	public String error(Model model) {
		model.addAttribute("error", "로그인 실패");
		return "/login";
	}
}
