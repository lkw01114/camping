package com.camping.home.bbs.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.camping.home.common.PropertyUtil;



/**
 *	PropertyUtil InitInterceptor
 */
public class InitInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		PropertyUtil.setPath(request);
		
		return true;
	}

}
