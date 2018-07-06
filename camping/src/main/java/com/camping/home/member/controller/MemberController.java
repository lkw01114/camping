package com.camping.home.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.camping.home.common.CommonUtil;
import com.camping.home.common.PasswordAuthentication;
import com.camping.home.member.model.Member;
import com.camping.home.member.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	@Autowired
	protected MemberService memberService;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private static final String URL_OF_COMMON = "/member/";
	
	/**
	 * 회원가입 폼
	 * @return
	 */
	@RequestMapping(value="joinMember", method=RequestMethod.GET)
	public String joinMemberGet() {
		logger.info("joinMember Get");
		return URL_OF_COMMON + "joinMember";
	}
	
	/**
	 * 회원가입 폼처리.
	 * @param member
	 * @param rttr
	 * @return
	 */
	@RequestMapping(value="joinMember", method=RequestMethod.POST)	
	public String joinMemberPost(Model model, @RequestParam Map<String,Object> params, RedirectAttributes rttr)throws Exception {
		logger.info("joinmember Post");
		
		// 기존 암화화 방식
		// PasswordAuthentication passAuth = new PasswordAuthentication();
		// String token = passAuth.hash(CommonUtil.nvlTrim(params.get("strJoinPass")).toCharArray());	
		
		// 시큐리티 비밀번호 암호화
		BCryptPasswordEncoder securityPass = new BCryptPasswordEncoder();
		String token = securityPass.encode(CommonUtil.nvlTrim(params.get("strJoinPass")));
		
		params.put("id", CommonUtil.nvlTrim(params.get("strJoinID")));
		//params.put("pass", token);
		params.put("pass", CommonUtil.nvlTrim(params.get("strJoinPass")));
		params.put("pass2", CommonUtil.nvlTrim(params.get("strJoinPasschk"))); 
		params.put("name", CommonUtil.nvlTrim(params.get("strJoinName"))); 
		params.put("email", CommonUtil.nvlTrim(params.get("strJoinEmail"))); 
		params.put("hp1", CommonUtil.nvlTrim(params.get("strJoinMobile1"))); 
		params.put("hp2", CommonUtil.nvlTrim(params.get("strJoinMobile2"))); 
		params.put("hp3", CommonUtil.nvlTrim(params.get("strJoinMobile3")));
		params.put("post", CommonUtil.nvlTrim(params.get("strJoinAddrNo")));
		params.put("addr", CommonUtil.nvlTrim(params.get("strJoinAddr1")));
		params.put("addr_detail", CommonUtil.nvlTrim(params.get("strJoinAddr2")));

		
		// Validation check 를 위해 한다. 
		Map<String, String> map = new HashMap<String, String>();
		map.put("strJoinID", CommonUtil.nvlTrim(params.get("strJoinID")));
		map.put("strJoinName", CommonUtil.nvlTrim(params.get("strJoinName")));
		map.put("strJoinEmail", CommonUtil.nvlTrim(params.get("strJoinEmail")));
		map.put("strJoinMobile1", CommonUtil.nvlTrim(params.get("strJoinMobile1")));
		map.put("strJoinMobile2", CommonUtil.nvlTrim(params.get("strJoinMobile2")));
		map.put("strJoinMobile3", CommonUtil.nvlTrim(params.get("strJoinMobile3")));
		map.put("strJoinAddrNo", CommonUtil.nvlTrim(params.get("strJoinAddrNo")));
		map.put("strJoinAddr1", CommonUtil.nvlTrim(params.get("strJoinAddr1")));
		map.put("strJoinAddr2", CommonUtil.nvlTrim(params.get("strJoinAddr2")));
		
		// 비밀번호 검사
		if(!CommonUtil.nvlTrim(params.get("strJoinPass")).equals(CommonUtil.nvlTrim(params.get("strJoinPasschk")))) {
			logger.info("비밀번호 상이!!");
			model.addAttribute("member", map);
			model.addAttribute("error", "입력하신 비밀번호가 다릅니다.");
			return URL_OF_COMMON + "joinMember";
		}
		
		// 아이디 중복검사
		if(memberService.isCheckId(params) > 0) {
			logger.info("아이디 중복!!");
			model.addAttribute("member", map);
			model.addAttribute("error", "아이디 중복입니다.");
			return URL_OF_COMMON + "joinMember";			
		}
		
		// 등록처리
		try {
			memberService.insertMember(params);
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("joinMember Error");
			model.addAttribute("member", map);
			model.addAttribute("error", "에러발생.");
			return URL_OF_COMMON + "joinMember";
		}
		
		rttr.addFlashAttribute("result", "success");
		return "redirect:/member/joinResult";
	}
	
	/**
	 * ȸ������ �Ϸ� ������
	 * @return
	 */
	@RequestMapping(value = "joinResult")
	public String joinResult() {
		logger.info("joinResult");
		return URL_OF_COMMON + "joinResult";
	}
	
	/**
	 * json return 
	 * @param model
	 * @param params
	 * @return
	 */
	@RequestMapping(value="IdCheck", method=RequestMethod.POST)
	public String IdCheck(Model model, @RequestParam Map<String,Object> params)throws Exception {
		logger.info("IdCheck");
		int result = memberService.isCheckId(params); 
		
		return CommonUtil.createObjectToJson(model,result);
	}
	
	/**
	 * login page
	 * @return
	 */
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String loginGet() {
		logger.info("loginGet");
		return URL_OF_COMMON + "login"; 
	}
	
	/**
	 * login Proc
	 * @param model
	 * @param params
	 * @return
	 */
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String loginPost(Model model, HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String,Object> params)throws Exception {
		logger.info("loginPost");
		
		// 이전 비밀번호 암호화 
		PasswordAuthentication passAuth = new PasswordAuthentication();
		// 시큐리티 비밀번호 암호화
		BCryptPasswordEncoder securityPass = new BCryptPasswordEncoder();
		
		String id 		= CommonUtil.nvlTrim(params.get("strLoginID"));
		String pass   = CommonUtil.nvlTrim(params.get("strLoginPwd"));
		String orgPath  = CommonUtil.nvlTrim(params.get("orgPath"));
		String strSaveID = CommonUtil.nvlTrim(params.get("strSaveID"));
		
		params.put("id", id);
		params.put("pass", pass);
		
		try {
			Member returnMember =  memberService.isValidMember(params);
			logger.info("returnMember>> " + returnMember);
			if(returnMember == null) {
				model.addAttribute("error", "등록된 아이디가 없습니다.");
				return URL_OF_COMMON + "login"; 
			}else {
				
				//if(!passAuth.authenticate(pass.toCharArray(), returnMember.getPassword() ) ) {
				//if(!securityPass.encode(pass).equals(returnMember.getPassword())) {
				//logger.info(">>>>>> securityPass.encode(pass) " + securityPass.encode(pass));
				if(!returnMember.getPassword().equals(pass)) {
					model.addAttribute("error", "비밀번호가 상이합니다.");
					return URL_OF_COMMON + "login"; 
				}
				
				HttpSession session = request.getSession();
				session.setAttribute("member", returnMember);

				// 아이디 저장
				if("".equals(strSaveID)) { 
					Cookie cookie = new Cookie("id",id);
					cookie.setMaxAge(0);	// 0으로 설정.
					response.addCookie(cookie);				
				}else { // 아이디 비저장
					Cookie cookie = new Cookie("id",id);
					cookie.setMaxAge(60*50*24*365);	// 1년으로 설정.
					response.addCookie(cookie);
				}	
				
				if("".equals(orgPath)) {
					return "redirect:/";
				}else {
					return orgPath;
				}				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", "에러가 발생했습니다.");
			return URL_OF_COMMON + "login"; 
		}
		
	}
	
	/**
	 * logout
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="logout")
	protected String doGet(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("member");
		String returnUrl = "";
		
		if(member != null) {
			session.invalidate();
			returnUrl = "redirect:/"; 
		
		}else {
			model.addAttribute("error", "먼저 로그인을 하세요");
			returnUrl = URL_OF_COMMON + "login";
		}
		
		return returnUrl;
	}
	
	
}
