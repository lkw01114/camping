package org.study.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CommonExceptionAdvice {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonExceptionAdvice.class);
	
	@ExceptionHandler(Exception.class)
	public ModelAndView common(Exception e) {
		logger.info("eeeeeeee >> " + e.toString());
		ModelAndView mv = new ModelAndView();
		mv.setViewName("common/error_common");
		mv.addObject("Exception", e);
		return mv;
	}
	
	
	
	
}
