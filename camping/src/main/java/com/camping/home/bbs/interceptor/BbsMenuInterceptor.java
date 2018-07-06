package com.camping.home.bbs.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.camping.home.common.CommonUtil;

/**
 * 게시판 Interceptor
 * @author 201-18
 *
 */
public class BbsMenuInterceptor extends HandlerInterceptorAdapter{

	/** slf4j Logger */
	//private static final Logger logger = LoggerFactory.getLogger(AdminBbsAuthCheckInterceptor.class);
	
	@Value("#{menu_prop}")
	private Map<String,String> menu;
	
	private String getMenu(String str){
		return CommonUtil.nvl(menu.get(str));
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		Map<?,?> params = CommonUtil.paramaterToMap(request.getParameterMap());
		
		int menuSeq = CommonUtil.nvl(params.get("menuseq"), 0);
		
		String m2Menu = "";
		
		//�������� - 13
		//���� �ڷ� - 14
		//��ȸ ����Ȱ�� - 190
		//ä�� - 205
		//�������� - 188
		//�������� ����� ��Ȳ  - 240
		//FAQ - 17
		//��������ҽ� - 150
		//�޻�� - 801
		switch(menuSeq)
		{
			case 13 : 	m2Menu = getMenu("a201"); break;
			case 14 : 	m2Menu = getMenu("a202"); break;
			case 190 : 	m2Menu = getMenu("a203"); break;
			case 205 : 	m2Menu = getMenu("a204"); break;
			case 188 : 	m2Menu = getMenu("a215"); break;
			case 240 :	m2Menu = getMenu("a218"); break;
			case 17 :	m2Menu = getMenu("a211"); break;
			case 150 :	m2Menu = getMenu("a217"); break;
			case 801 :	m2Menu = getMenu("a207"); break;
			case 803 :	m2Menu = getMenu("a208"); break;
			case 805 :	m2Menu = getMenu("a209"); break;
			case 555 :	m2Menu = getMenu("a210"); break;
			case 204 :	m2Menu = getMenu("a219"); break;
			case 901 :	m2Menu = getMenu("a205"); break;
			case 903 :	m2Menu = getMenu("a206"); break;
			case 301 :	m2Menu = getMenu("a212"); break;
		}
		
		HashMap<Object, String> menuMap = new HashMap<Object, String>();
		menuMap.put("M1_MENU", getMenu("a2"));
		menuMap.put("M1_URL", "/admin/bbs/bbs_list.do?menuseq=13");
		menuMap.put("M2_MENU", m2Menu);
		menuMap.put("M2_URL", "/admin/bbs/bbs_list.do?menuseq=13");
		
		request.setAttribute("menu_info", menuMap);
		
		return true;
	}

}
