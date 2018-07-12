package com.camping.home.common;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * global.properties  에 있는 내용을 가져옵니다.
 */
public class PropertyUtil {

	private static final Logger logger = LoggerFactory.getLogger(PropertyUtil.class);
	
	private static HashMap<String, String> props = null;
	private static HashMap<String, String> props2 = null;
	
	/**
	 * 환경 구분
	 * 
	 * 로컬 :0, 개발 : 1, 운영 : 2
	 * 초기화 진행전 : -1
	 */
	private static int devFlag = -1;
	
	/**
	 * 환경 구분
	 * 
	 * 로컬 :0, 개발 : 1, 운영 : 2
	 * 초기화 진행전 : -1
	 */
	public static int getDevFlag() {
		return devFlag;
	}

	public PropertyUtil() {
		
	}
	
	public static void init()
	{
		props = new HashMap<String, String>();
		props2 = new HashMap<String, String>();

		String osName = CommonUtil.nvl(System.getProperty("os.name"));
		
		ResourceBundle rb = ResourceBundle.getBundle("global");
		ResourceBundle rb2 = ResourceBundle.getBundle("message");

		Enumeration<?> enumData = rb.getKeys();
		while (enumData.hasMoreElements()) 
		{
			String enumResult = (String) enumData.nextElement();
			props.put(enumResult, rb.getString(enumResult));
		}
		props.put("osName", osName);
		
		
		Enumeration<?> enumData2 = rb2.getKeys();
		while (enumData2.hasMoreElements()) 
		{
			String enumResult = (String) enumData2.nextElement();
			props2.put(enumResult, rb2.getString(enumResult));
		}
	}
	
	public static void setPath(HttpServletRequest request){
		if(props == null) init();
		if(props.get("contextRealPath") == null)
		{
			logger.info("in~~~~~~~~~~~~~");
			String tmp = request.getSession().getServletContext().getRealPath("/WEB-INF/").replace("\\", "/") + "/";
			logger.info("tmp >>>" + tmp);
			props.put("contextRealPath", tmp);
			props.put("rootPath", tmp.replace("/WEB-INF/", "/"));
			props.put("staticPath", tmp.replace("/WEB-INF/", "/static/"));
			devFlag = 0;
			props.put("devFlag", ""+devFlag);
		}
	}
	
	public static String getProperty(String name)
	{
		if(props == null) init();
		
		try 
		{
			return CommonUtil.encodingString((String)props.get(name), "8859_1", "euc-kr");
		}
		catch(Exception e)
		{
			logger.info("getProperty Fail key Name : "+name);
			return null;
		}
	}
	public static String getMessage(String name)
	{
		if(props2 == null) init();
		
		try 
		{
			return CommonUtil.nvl(CommonUtil.encodingString((String)props2.get(name), "8859_1", "euc-kr"));
		}
		catch(Exception e)
		{
			logger.info("getMessage Fail key Name : "+name);
			return null;
		}
	}
}