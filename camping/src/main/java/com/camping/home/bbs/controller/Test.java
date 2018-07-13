package com.camping.home.bbs.controller;

import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {
	
    private static final Logger logger = LoggerFactory.getLogger(Test.class);


	public static void main(String[] args) {
		String strName = "/bbs1/3ddeb1a3-a9af-483d-97e1-1d9a9aaa975f_Lighthouse.jpg||/bbs1/46bb296f-61e0-40da-876a-cd4a99fb67dc_Koala.jpg";
		
		String[] spStrNames = strName.split("\\|\\|");
		for(String f : spStrNames) {
			logger.info(f);
		}
		
		
		StringTokenizer st = new StringTokenizer(strName, "||");
		for(int i=0; st.hasMoreTokens(); i++) {
			logger.info(st.nextToken());
		}

	}

}
