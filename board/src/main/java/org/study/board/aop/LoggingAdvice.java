package org.study.board.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAdvice {

	private static final Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);
	
	@Before("execution(* org.study.board.service.BoardServiceImpl.*(..))")
	public void startLog(JoinPoint jp) {
		logger.info("startLog-------------------------------");
		
		logger.info(jp.getSignature().toString());
		
		logger.info(Arrays.toString(jp.getArgs()));
	}
	
	@Around("execution(* org.study.board.service.BoardServiceImpl.read*(..))")
	public Object readLog(ProceedingJoinPoint pjp)throws Throwable{
		long startTime = System.currentTimeMillis();
		Object obj = pjp.proceed();
		long endTime = System.currentTimeMillis();
		
		logger.info(pjp.getSignature().toString() + " : " + (endTime - startTime) + "(ms)");
				
		return obj;
	}
	
}
