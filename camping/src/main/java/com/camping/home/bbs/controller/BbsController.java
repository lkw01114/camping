package com.camping.home.bbs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/bbs/*")
public class BbsController {
	
	//logger
	public static final Logger logger = LoggerFactory.getLogger(BbsController.class);
	// 
	private static final String URL_OF_COMMON = "/bbs/";
	
	/**
	 * 게시판 글쓰기 폼페이지 이동
	 * @param model
	 * @param menuseq
	 * @return
	 */
	@RequestMapping(value = "bbs_write")
	public String bbsWrite(Model model) {
		logger.info("bbs_write action");
		//model.addAttribute("menuseq", menuseq);
		return URL_OF_COMMON + "bbs_write";
	}
	
	
}
