package com.camping.home.bbs.service;

import java.util.List;
import java.util.Map;

import com.camping.home.bbs.model.Bbs;



public interface BbsService {

	int pager = 10;	
	
	public List<Bbs> listCategory() throws Exception;
	
	public int getMaxRef() throws Exception;
	
	public int insertBbs(Map params) throws Exception;
}
