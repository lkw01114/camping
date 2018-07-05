package com.camping.home.member.service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.camping.home.common.CommonUtil;
import com.camping.home.member.model.Member;

@Service
public class MemberServiceImpl implements MemberService{

	private static final String name = "com.camping.home.mapper.MemberMapper";
	
	@Autowired
	SqlSession session;	
	
	@Override
	public int isCheckId(Map<String, Object> params) throws Exception{
		return CommonUtil.nvl(session.selectOne(name + ".isCheckId", params),0);
	}

	@Override
	public void insertMember(Map params) throws Exception {
		session.insert(name + ".insertMember", params);
	}

	@Override
	public Member isValidMember(Map params) throws Exception {
		return session.selectOne(name + ".isValidMember", params);
	}
	
	

}
