package com.camping.home.introduce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.camping.home.member.controller.MemberController;

@Controller
@RequestMapping("/introduce/*")
public class IntroduceController {
	
	private static final Logger logger = LoggerFactory.getLogger(IntroduceController.class);
	private static final String URL_OF_COMMON = "/introduce/";
	
	/**
	 * 캠핑장 소개 페이지
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="introduce")
	public String introduce() throws Exception {
		return URL_OF_COMMON + "introduce";
	}
}
