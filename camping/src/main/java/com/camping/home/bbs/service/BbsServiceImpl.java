package com.camping.home.bbs.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camping.home.bbs.model.Bbs;

@Service
public class BbsServiceImpl implements BbsService {
	
	private static final String name = "com.camping.home.mapper.BbsMapper";
	
	@Autowired
	SqlSession session;			

	@Override
	public List<Bbs> listCategory() throws Exception {
		return session.selectList(name + ".listCategory");
	}

	@Override
	public int insertBbs(Map params) throws Exception {
		//Bbs bbs = new Bbs();
		//bbs = 
		return session.insert(name +  ".insertBbs", params);  
	}

	@Override
	public int getMaxRef() throws Exception {
		return session.selectOne(name +  ".getMaxRef");
	}

	@Override
	public int insertFile(Map params) throws Exception {
		return session.insert(name + ".inertFile", params);
	}

	@Override
	public List<Bbs> selectBbsList(Map params) throws Exception {
		return session.selectList(name + ".selectBbsList", params);
	}

	@Override
	public int selectBbsCount(Map params) throws Exception {
		return session.selectOne(name + ".selectBbsCount", params);
	}

	@Override
	public Bbs detailBbs(Map params) throws Exception {
		return session.selectOne(name + ".detailBbs", params);
	}

}
