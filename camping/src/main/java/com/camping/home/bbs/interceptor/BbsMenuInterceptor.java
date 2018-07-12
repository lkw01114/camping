package com.camping.home.bbs.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.camping.home.common.CommonUtil;
import com.camping.home.member.model.Member;

/**
 * 게시판 Interceptor
 * @author 201-18
 *
 */
public class BbsMenuInterceptor extends HandlerInterceptorAdapter{

	/** slf4j Logger */
	private static final Logger logger = LoggerFactory.getLogger(BbsMenuInterceptor.class);

	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		
		Map<?,?> params = CommonUtil.paramaterToMap(request.getParameterMap());
		
		int menuSeq = CommonUtil.nvl(params.get("menuseq"), 0);
		
		logger.info("menuSeq >> " + menuSeq);
		
		return true;
	}

}
