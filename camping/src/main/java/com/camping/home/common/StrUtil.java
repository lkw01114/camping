package com.camping.home.common;

public class StrUtil {
	
	/**
	 * String Null Check
	 * @param s
	 * @return
	 */
	public static String checkNull(String s) {
		String s1 = "";
		
		if(s == null || s.equals("") || s.equals("null"))
			s1 = "";
		else
			s1 = s.trim();
		
		return s1;
	}
	
	/**
	 * Null Check �썑 蹂��솚
	 * @param srcstr
	 * @param repstr
	 * @return
	 */
	public static String NVL(String srcstr, String repstr) {
        return ( srcstr == null ) ? repstr : srcstr;
    }
	
	/**
	 * 臾몄옄�뿴以묒뿉�꽌 �듅�젙臾몄옄瑜� 蹂��솚
	 * @param v1 : 臾몄옄�뿴
	 * @param v2 : �듅�젙臾몄옄
	 * @param v3 : 蹂��솚�맆 臾몄옄
	 * @return
	 */
	public static String replaceStr(String v1, String v2, String v3){
		String r = "";
		if(!checkNull(v1).equals("")){
			r = replaceStr2(v1,v2,v3);
		}
		return r;
	}	
	
    public static String replaceStr2(String s, String s1, String s2)
    {
        int i = 0;
        boolean flag = false;
        StringBuffer stringbuffer = new StringBuffer();
        for(int j = s.indexOf(s1, i); j >= 0; j = s.indexOf(s1, i))
        {
            stringbuffer.append(s.substring(i, j));
            stringbuffer.append(s2);
            i = j + s1.length();
        }

        if(i <= s.length())
            stringbuffer.append(s.substring(i, s.length()));
        return stringbuffer.toString();
    }	
}
