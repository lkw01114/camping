package com.camping.home.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.camping.home.member.controller.MemberController;
import com.camping.home.member.model.Member;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

	/** slf4j Logger */
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	private static final String URL_OF_LOGIN = "/member/login";
	
	@SuppressWarnings("unused")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		// session check
		HttpSession session = request.getSession();
		Member member = new Member();
		logger.info("session Check>>>>>>>>>>>>>>");
		
		if(session.getAttribute("member") == null) {
			logger.info("session null>>>>>");
			response.sendRedirect(URL_OF_LOGIN);
			return false;
		}else {
			member = (Member)session.getAttribute("member");
		}
		
		
		if(StrUtil.checkNull(member.getId()).equals("")) {
			logger.info("session id null>>>>>");
			response.sendRedirect(URL_OF_LOGIN);
			return false;
		}
		return true;
	}

}



