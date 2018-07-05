package com.camping.home.member.service;

import java.util.Map;

import com.camping.home.member.model.Member;

public interface MemberService {

	public int isCheckId(Map<String, Object> params)throws Exception;
	
	public void insertMember(Map params) throws Exception;
	
	public Member isValidMember(Map params)  throws Exception;
	
	
	
}
