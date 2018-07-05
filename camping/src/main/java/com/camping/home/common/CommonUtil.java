package com.camping.home.common;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


@SuppressWarnings({ "unchecked", "rawtypes" })
public class CommonUtil {

	static public boolean isEmpty(Object arg){
		boolean result = false;
		if(arg == null || String.valueOf(arg).trim().equals("")){
			result = true;
		}
		return result;
	}


	static public boolean isNull(){
		return true;
	}


	static public String nvl(Object arg, String ch){
		if(arg == null) return ch;
		String str = String.valueOf(arg);
		if(str.trim().equals("") || str.trim().equalsIgnoreCase("null")) return ch;
		return str;
	}


	static public String nvl(Object arg){
		return nvl(arg, "");
	}
	static public String nvlTrim(Object arg){
		return nvl(arg, "").trim();
	}
	static public String nvlTrim(Object arg, String ch){
		return nvl(arg, ch).trim();
	}


	static public String nvlyn(Object arg){
		return nvl(arg, "N");
	}


	static public boolean isNotEmpty(Object arg){
		return !isEmpty(arg);
	}
	static public boolean isNotEmpty(Object arg,Object arg2){
		boolean returnValue = true;
		returnValue = !isEmpty(arg);
		returnValue = !isEmpty(arg2);
		return returnValue;
	}


	static public int nvl(Object arg, int i){
		if(arg == null) return i;
		int returnValue;
		try{
			returnValue = Integer.valueOf(nvl(arg, "0"));
		}catch(Exception e){
			return i;
		}
		if(returnValue == 0) returnValue = i;
		return returnValue;
	}

	static public float nvlFloat(Object arg, float i){
		if(arg == null) return i;
		float returnValue;
		try{
			returnValue = Float.valueOf(nvl(arg, "0"));
		}catch(Exception e){
			return i;
		}
		if(returnValue == 0) returnValue = i;
		return returnValue;
	}

	static public Double nvlDouble(Object arg, Double i){
		if(arg == null) return i;
		Double returnValue;
		try{
			returnValue = Double.valueOf(nvl(arg, "0"));
		}catch(Exception e){
			return i;
		}
		if(returnValue == 0) returnValue = i;
		return returnValue;
	}

	static public int nvlInt(Object arg){
		return nvl(arg, 0);
	}


	static public HashMap paramaterToMap(Map params){
		HashMap data = new HashMap<String, Comparable>();
		Iterator iter = params.keySet().iterator();
		while(iter.hasNext()){
			String key = CommonUtil.nvl(iter.next());
			String[] values = (String[])params.get(key);
			if(values.length > 1){
				data.put(key, values);
			}else{
				data.put(key, CommonUtil.nvl(values[0]));
			}
		}
		return data;
	}


	static public String makeTimeKey(String id, int randomSize){
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
		return id + sdf.format(now) + (int)(Math.random()*randomSize);
	}

	static public String makeTimeKey(){
		return makeTimeKey("", 10000);
	}


	static public String encodingString(String str, String fromEncoding, String toEncoding){
		String result = str;
		try{
			result = new String(str.getBytes(fromEncoding), toEncoding);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}


	static public String encodingString(String str, String encoding, int type){
		String result = str;
		try{
			if(type == 0){
				result = new String(str.getBytes(encoding));
			}else{
				result = new String(str.getBytes(), encoding);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}


	static public String dateAreaSet(String lang, String date){
		String result = date;
		try{
			if(lang == "KOREAN" || lang == "CHINESE"){
				result = new String(date.substring(1, 4)+"-"+date.substring(5,2)+"-"+date.substring(7,2));
			}else{
				result = new String(date.substring(5, 2)+"-"+date.substring(7,2)+"-"+date.substring(1,4));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}


	static public void encodingTest(String str){
		System.out.println("encoding test : " + str);
		System.out.println("ms949     ===> utf-8     : " + encodingString(str, "ms949", "utf-8"));
		System.out.println("ms949     ===> euc-kr     : " + encodingString(str, "ms949", "euc-kr"));
		System.out.println("ms949     ===> 8859_1     : " + encodingString(str, "ms949", "8859_1"));
		System.out.println("8859_1     ===> utf-8     : " + encodingString(str, "8859_1", "utf-8"));
		System.out.println("8859_1     ===> euc-kr     : " + encodingString(str, "8859_1", "euc-kr"));
		System.out.println("8859_1     ===> ms949     : " + encodingString(str, "8859_1", "ms949"));
		System.out.println("utf-8     ===> 8859_1     : " + encodingString(str, "utf-8", "8859_1"));
		System.out.println("utf-8     ===> euc-kr     : " + encodingString(str, "utf-8", "euc-kr"));
		System.out.println("utf-8     ===> ms949     : " + encodingString(str, "utf-8", "ms949"));
		System.out.println("euc-kr     ===> 8859_1     : " + encodingString(str, "euc-kr", "8859_1"));
		System.out.println("euc-kr     ===> utf-8     : " + encodingString(str, "euc-kr", "utf-8"));
		System.out.println("euc-kr     ===> ms949     : " + encodingString(str, "euc-kr", "ms949"));
		System.out.println("getbytes euc-kr     : " + encodingString(str, "euc-kr", 0));
		System.out.println("getbytes utf-8     : " + encodingString(str, "utf-8", 0));
		System.out.println("getbytes 8859_1     : " + encodingString(str, "8859_1", 0));
		System.out.println("getbytes ms949: " + encodingString(str, "ms949", 0));
		System.out.println("charset euc-kr     : " + encodingString(str, "euc-kr", 1));
		System.out.println("charset utf-8     : " + encodingString(str, "utf-8", 1));
		System.out.println("charset 8859_1     : " + encodingString(str, "8859_1", 1));
		System.out.println("charset ms949     : " + encodingString(str, "ms949", 1));
	}


	static public String formatDate(Object arg){
		String str = nvl(arg).replace("-", "").replace(":", "");
		if(isEmpty(arg)) return "";
		StringBuffer result = new StringBuffer();
		for(int i=0; i<str.length(); i++){
			if(i==4 || i==6){
				result.append("-");
			}
			if(i==8){
				result.append(" ");
			}
			if(i==10 || i==12){
				result.append(":");
			}
			result.append(str.charAt(i));
		}
		return result.toString();
	}


	static public String strCut(String szText, int nLength) { // ���ڿ� �ڸ���
		String r_val = szText;
		int oF = 0, oL = 0, rF = 0, rL = 0;
		int nLengthPrev = 0;
		try {
			byte[] bytes = r_val.getBytes("UTF-8"); // ����Ʈ�� ����
			// x���� y���̸�ŭ �߶󳽴�. �ѱ۾ȱ�����.
			int j = 0;
			if (nLengthPrev > 0)
				while (j < bytes.length) {
					if ((bytes[j] & 0x80) != 0) {
						oF += 2;
						rF += 3;
						if (oF + 2 > nLengthPrev) {
							break;
						}
						j += 3;
					} else {
						if (oF + 1 > nLengthPrev) {
							break;
						}
						++oF;
						++rF;
						++j;
					}
				}
			j = rF;
			while (j < bytes.length) {
				if ((bytes[j] & 0x80) != 0) {
					if (oL + 2 > nLength) {
						break;
					}
					oL += 2;
					rL += 3;
					j += 3;
				} else {
					if (oL + 1 > nLength) {
						break;
					}
					++oL;
					++rL;
					++j;
				}
			}
			r_val = new String(bytes, rF, rL, "UTF-8"); // charset �ɼ�
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return r_val;
	}

	/**
	 * <PRE>
	 * String�� null ���� �����ڷ� ��ȯ.
	 * </PRE>
	 * @param String str
	 * @return String
	 */
	public static String nullCheck(String str) {
		return (str == null) ? "" : str;
	}


	/**
	 * <PRE>
	 * String�� null ���� "0"�� ��ȯ.
	 * </PRE>
	 * @param String str
	 * @return String
	 */
	public static String nullZeroCheck(String str) {
		if (str == null || str.equals("") || str.equals("-")
				|| str.equals("-0")) {
			return "0";
		} else {
			return str;
		}
	}


	/**
	 * <PRE>
	 * fIdx���� tIdx���� ���ڿ��� �ڸ���.
	 * </PRE>
	 * @param src String
	 * @param fIdx int
	 * @param tIdx int
	 * @return String
	 */
	public static String substring(String src, int fIdx, int tIdx) {
		if (src == null)
			return "";

		if (fIdx < 0) {
			fIdx = 0;
		} else if (fIdx > src.length()) {
			fIdx = src.length();
		}

		if (tIdx < 0) {
			tIdx = 0;
		} else if (tIdx > src.length()) {
			tIdx = src.length();
		}

		if (fIdx == tIdx) {
			return src.substring(tIdx);
		} else if (fIdx > tIdx) {
			return "";
		} else {
			return src.substring(fIdx, tIdx);
		}
	}


	/**
	 * <PRE>
	 * fIdx���� ���ڿ��� �ڸ���.
	 * </PRE>
	 * @param src String, fIdx int
	 * @return String
	 */
	public static String substring(String src, int fIdx) {
		if (src == null)
			return "";

		if (fIdx < 0) {
			fIdx = 0;
		} else if (fIdx > src.length()) {
			fIdx = src.length();
		}
		return src.substring(fIdx);
	}


	/**
	 * <PRE>
	 * �ΰ��� ��Ʈ�����ε� ���� DATA�� ������ ���밪�� ���Ѵ�.
	 * </PRE>
	 * @param strNum1
	 * @param strNum2
	 * @return String ���밪�� ��ȯ�Ѵ�.
	 */
	public static String absString(String strNum1, String strNum2) {
		try {
			return Long.toString(Math.abs(Long.parseLong(strNum1)
					- Long.parseLong(strNum2)));
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}


	/**
	 * <PRE>
	 * ��ȭ ������ ��Ʈ������ ��ȯ
	 * </PRE>
	 * @param strNum
	 * @return ###,### ������ ��Ʈ������ ��ȯ�Ѵ�.
	 */
	public static String toPrice(String strNum) {
		// int nNum = Integer.parseInt(strNum);
		if (strNum == null || strNum.equals("")) {
			return "";
		} else {
			Double dNum = Double.parseDouble(strNum);
			return NumberFormat.getInstance(Locale.KOREA).format(dNum);
		}
	}


	/**
	 * <PRE>
	 * LONG�� �� ��ȭ������ �ٲ۴�
	 * </PRE>
	 * @param long money
	 * @return String
	 * @exception
	 * @see
	 */
	public static String getMoney(long money) {
		String conMoney = "0";

		if (money != 0) {
			DecimalFormat df = new DecimalFormat("###,###,###");
			conMoney = df.format(money);
		}
		return conMoney;
	}


	/**
	 * <PRE>
	 * STRING�� INT������ ��ȯ�Ѵ�.
	 * </PRE>
	 * @param str int������ ��ȯ�� String���ڿ�.
	 * @return ��ȯ�� int ��.
	 */
	public static int toInt(String str) {
		if (str == null) {
			return 0;
		}
		return (Integer.valueOf(str).intValue());
	}


	/**
	 * <PRE>
	 * ���ڿ����� ,�� �����ϰ� INT������ ��ȯ
	 * </PRE>
	 * @param str int������ ��ȯ�� String���ڿ�.
	 * @return ��ȯ�� int ��.
	 */
	public static int ChangeInteger(String str) {
		int rtn = 0;
		if (null != str && !"".equals(str)) {
			rtn = Integer.parseInt(delChar(str, ","));
		} else {
			rtn = 0;
		}
		return rtn;
	}


	/**
	 * <PRE>
	 * ���ڿ����� ,�� �����ϰ� long������ ��ȯ
	 * </PRE>
	 * @param str int������ ��ȯ�� String���ڿ�.
	 * @return ��ȯ�� int ��.
	 */
	public static Double ChangeDouble(String str) {
		Double rtn = 0.0;
		if (null != str && !"".equals(str)) {
			rtn = Double.parseDouble(delChar(str, ","));
		} else {
			rtn = 0.0;
		}
		return rtn;
	}


	/**
	 * <PRE>
	 * int���� String���� ��ȯ�Ѵ�.
	 * </PRE>
	 * @param i String���� ��ȯ�� int ��.
	 * @return ��ȯ�� String ��.
	 */
	public static String toStr(int i) {
		return (new Integer(i).toString());
	}


	/**
	 * <PRE>
	 * Date type���� �Ѿ�� ��¥�� ����Ʈ ����(YYYY-MM-DD)���� ��ȯ�Ѵ�.<BR>
	 * </PRE>
	 * @param date ��¥
	 * @return String ��ȯ�� ���ڿ�
	 */
	public static String toStr(Date date) {
		return toStr(date, "ymd");
	}


	/**
	 * Date type���� �Ѿ�� ��¥�� ����Ʈ ����(YYYY-MM-DD)���� ��ȯ�Ѵ�.<BR>
	 * @param date ��¥
	 * @param format ��ȯ�� Ÿ��
	 * @return String ��ȯ�� ���ڿ�
	 */
	public static String toStr(Date date, String format) {
		return toStr(date, format, "-");
	}


	/**
	 * Date type���� �Ѿ�� ���ڸ� ������ ���˿� ���� �籸���Ͽ� ��ȯ�Ѵ�.<BR>
	 * @param date ��¥ ���ڿ�
	 * @param format ��ȯ�� Ÿ��
	 * @param formatStr
	 *            ��ȯ�� ���� ���ڿ� y : yyyy ym : yyyy-mm ymd : yyyy-mm-dd ymdh :
	 *            yyyy-mm-dd hh ymdhm : yyyy-mm-dd hh:m ymdhms : yyyy-mm-dd
	 *            hh:m:ss
	 * @return String ��ȯ�� ���ڿ�
	 */
	public static String toStr(Date date, String format, String formatStr) {
		SimpleDateFormat sdf = null;

		if (date == null) {
			return "";
		}

		if (!"-".equals(formatStr) && !"/".equals(formatStr)
				&& !".".equals(formatStr)) {
			formatStr = "-";
		}

		if ("y".equals(format)) {
			sdf = new SimpleDateFormat("yyyy");
		} else if ("ym".equals(format)) {
			sdf = new SimpleDateFormat("yyyy" + formatStr + "MM");
		} else if ("ymd".equals(format)) {
			sdf = new SimpleDateFormat("yyyy" + formatStr + "MM" + formatStr
					+ "dd");
		} else if ("ymdh".equals(format)) {
			sdf = new SimpleDateFormat("yyyy" + formatStr + "MM" + formatStr
					+ "dd HH");
		} else if ("ymdhm".equals(format)) {
			sdf = new SimpleDateFormat("yyyy" + formatStr + "MM" + formatStr
					+ "dd HH:m");
		} else if ("ymdhms".equals(format)) {
			sdf = new SimpleDateFormat("yyyy" + formatStr + "MM" + formatStr
					+ "dd HH:m:ss");
		} else if ("h".equals(format)) {
			sdf = new SimpleDateFormat("HH");
		} else if ("hm".equals(format)) {
			sdf = new SimpleDateFormat("HH:m");
		} else if ("hms".equals(format)) {
			sdf = new SimpleDateFormat("HH:m:ss");
		}
		return (sdf.format(date));
	}


	/**
	 * <PRE>
	 * ��Ʈ���� �����ʿ� Ư���� ���ڸ� �ٿ� ������ ������ ���ڿ��� ��ȯ�Ѵ�.
	 * </PRE>
	 * @param String origin, String pStr, int length
	 * @return String
	 */
	public static String rightPadding(String origin, String pStr, int length) {
		int oL = origin.length();
		String rStr = origin;

		if (oL < length) {
			int dL = length - oL;
			for (int i = 0; i < dL; i++) {
				rStr = rStr.concat(pStr.substring(0, 1));
			}
		}
		return rStr;
	}


	/**
	 * <PRE>
	 * ��Ʈ���� ���ʿ� Ư���� ���ڸ� �ٿ� ������ ������ ���ڿ��� ��ȯ�Ѵ�.
	 * </PRE>
	 * @param String origin, String pStr, int length
	 * @return String
	 */
	public static String leftPadding(String origin, String pStr, int length) {
		int oL = origin.length();
		String rStr = origin;

		if (oL < length) {
			int dL = length - oL;
			for (int i = 0; i < dL; i++) {
				rStr = pStr.substring(0, 1).concat(rStr);
			}
		}
		return rStr;
	}


	/**
	 * <PRE>
	 * ���ڿ��� Ư�� ĳ���͸� �����Ѵ�.
	 * </PRE>
	 * @param String str
	 * @param String delim
	 * @return String
	 */
	public static String delChar(String str, String delim) {
		return delChar(nullCheck(str), delim.charAt(0));
	}


	/**
	 * <PRE>
	 * ���ڿ��� Ư�� ĳ���͸� �����Ѵ�.
	 * </PRE>
	 * @param str �ҽ� ���ڿ�.
	 * @param delim �ҽ����ڿ����� �����ϰ��� �ϴ� char.
	 * @return String
	 */
	public static String delChar(String str, char delim) {
		if (str == null || str.length() == 0 || delim == 0) {
			return str;
		}
		char[] chars = str.toCharArray();
		StringBuffer buffer = new StringBuffer(str.length());
		int len = chars.length;
		for (int i = 0; i < len; i++) {
			if (chars[i] != delim)
				buffer.append(chars[i]);
		}
		return buffer.toString();
	}


	/**
	 * <PRE>
	 * ? �� �ִ��� ������ ���θ� ��ȯ�Ѵ�.
	 * </PRE>
	 * @param str �ҽ� ���ڿ�.
	 * @param delim �ҽ����ڿ����� ã�����ϴ� �ϴ� char.
	 * @return int 0 : ���� 1 : ����
	 */
	public static int findChar(String str, char delim) {
		if (str == null || str.length() == 0 || delim == 0) {
			return 0;
		}
		int cnt = 0;
		char[] chars = str.toCharArray();
		int len = chars.length;
		for (int i = 0; i < len; i++) {
			if (chars[i] == delim) {
				cnt++;
			}
		}
		return cnt;
	}


	/**
	 * <PRE>
	 * �ۼ�Ʈ ���ڿ��� �Ҽ��� ���ڿ��� ��ȯ�Ѵ�.
	 * </PRE>
	 * 33 -> 0.33
	 * @param String sNum
	 * @return String
	 */
	public static String convertToRealNum(String sNum) {
		if (sNum == null || "".equals(sNum)) {
			return "0.0";
		} else {
			return (Double.parseDouble(sNum) / 100) + "";
		}
	}


	/**
	 * <PRE>
	 * �Ҽ��� ���ڿ��� �ۼ�Ʈ ���ڿ��� ��ȯ�Ѵ�.
	 * </PRE>
	 * 0.33 -> 33
	 * @param String sNum
	 * @return String
	 */
	public static String convertToPercent(String sNum) {
		if (sNum == null || "".equals(sNum)) {
			return "0";
		} else {
			return (Double.parseDouble(sNum) * 100) + "";
		}
	}


	/**
	 * <PRE>
	 * ���ڿ� ��ȯ
	 * </PRE>
	 * @param String str : ��ȯ�ؾ��� ���ڿ��� ���Ե� ���� ���ڿ�,String ��ȯ���ڿ�,String ġȯ���ڿ�
	 * @return String
	 */
	public static String replaceAllString(String str, String strFrom,
			String serTo) {
		if (str != null) {
			str = str.replaceAll(strFrom, serTo);
		}
		return str;
	}

	public static String replaceAllString(Object obj, String strFrom, String serTo){
		return replaceAllString(CommonUtil.nvl(obj), strFrom, serTo);
	}

	/**
	 * <PRE>
	 * ���ڿ� ������� ����Ͽ� double�� ����
	 * </PRE>
	 * @param String sArithmetic : �����
	 * @param int scale : �Ҽ� �ڸ���
	 * @return double : ���� ��
	 */
	public static String calculateString(String sArithmetic, int scale) {

		BigDecimal bdTemp = new BigDecimal(0);
		BigDecimal bdTemp1 = null;
		BigDecimal bdTemp2 = null;

		String sTemp = "";

		int iLeftIndex = 0;
		int iRightIndex = 0;

		ArrayList alArithmetic = new ArrayList();
		ArrayList alTemp = new ArrayList();

		// ������� ��������
		sArithmetic = removeSpaceAll(sArithmetic);

		try {
			// ��ȣ�� �ִ� ��� ��ȣ ���� ���� ���ȣ��
			if (sArithmetic.indexOf(")") > 0) {
				while (sArithmetic.indexOf(")") > 0) {
					iRightIndex = sArithmetic.indexOf(")");
					sTemp = sArithmetic.substring(0, iRightIndex);

					iLeftIndex = sTemp.lastIndexOf("(");
					sTemp = sTemp.substring(iLeftIndex + 1);

					sArithmetic = sArithmetic.substring(0, iLeftIndex)
							+ calculateString(sTemp, scale)
							+ sArithmetic.substring(iRightIndex + 1);
				}
			}

			// ������� �Ľ�
			StringTokenizer st = new StringTokenizer(sArithmetic, "*/%+-", true);
			while (st.hasMoreTokens()) {
				sTemp = st.nextToken();
				if (isOperand(sTemp) == 0)
					alTemp.add((new BigDecimal(sTemp)).setScale(scale,
							BigDecimal.ROUND_HALF_UP));
				else
					alTemp.add(sTemp);
			}

			// ���� ���� ó��
			for (int i = 0; i < alTemp.size(); i++) {

				// ���� ��ȣ(+,-)�� �����ϸ�
				if (i == 0 && alTemp.get(i) instanceof String) {
					if ("-".equals((String) alTemp.get(i))
							|| "+".equals((String) alTemp.get(i))) {
						alArithmetic.add((new BigDecimal(alTemp.get(i) + ""
								+ alTemp.get(i + 1))).setScale(scale,
										BigDecimal.ROUND_HALF_UP));
						i += 1;
					}
				} else {
					alArithmetic.add(alTemp.get(i));
				}

				// ���� ���ڰ� �ִ� ���
				if (alTemp.get(i) instanceof String
						&& alTemp.get(i + 1) instanceof String) {
					if ("-".equals((String) alTemp.get(i + 1))) {
						alArithmetic.add((new BigDecimal(alTemp.get(i + 1) + ""
								+ alTemp.get(i + 2))).setScale(scale,
										BigDecimal.ROUND_HALF_UP));
						i += 2;
					}
				}
			}
			alTemp.clear();

			// 3���� ������(*,/,%) ���� ���
			for (int i = 0; i < alArithmetic.size(); i++) {
				if (alArithmetic.get(i) instanceof BigDecimal) {
					alTemp.add(alArithmetic.get(i)); // ����
				} else {
					if (isOperand((String) alArithmetic.get(i)) == 3) {

						bdTemp1 = (BigDecimal) alTemp.get(alTemp.size() - 1);
						bdTemp2 = (BigDecimal) alArithmetic.get(i + 1);

						switch (((String) alArithmetic.get(i)).charAt(0)) {
						case '*':
							bdTemp = bdTemp1.multiply(bdTemp2);
							break;
						case '/':
							bdTemp = bdTemp1.divide(bdTemp2, scale,
									BigDecimal.ROUND_HALF_UP);
							break;
						case '%':
							bdTemp = bdTemp1.subtract(bdTemp2.multiply(bdTemp1
									.divide(bdTemp2, BigDecimal.ROUND_DOWN)));
							break;
						}

						alTemp.remove(alTemp.size() - 1);
						alTemp.add(bdTemp);
						i++; // 3���� ������ ���� ���ڴ� ���Ǿ����Ƿ�

					} else
						alTemp.add(alArithmetic.get(i)); // 5���� ������(+,-)
				}
			}

			// 5���� ������(+,-) ���
			for (int i = 0; i < alTemp.size(); i++) {

				if (i == 0)
					bdTemp = (BigDecimal) alTemp.get(i);

				if (alTemp.get(i) instanceof String) {
					if (isOperand((String) alTemp.get(i)) == 5) {

						bdTemp2 = (BigDecimal) alTemp.get(i + 1);

						switch (((String) alTemp.get(i)).charAt(0)) {
						case '+':
							bdTemp = bdTemp.add(bdTemp2);
							break;
						case '-':
							bdTemp = bdTemp.subtract(bdTemp2);
							break;
						}
						i++; // 5���� ������ ���� ���ڴ� ���Ǿ����Ƿ�
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1 + "";
		}
		return bdTemp.doubleValue() + "";
	}


	/**
	 * <PRE>
	 * ���������� �ƴ��� üũ
	 * </PRE>
	 *
	 * @param String op üũ�� ����
	 * @return int ���� -> 0, () - >1, /*% -> 3, +- -> 5
	 */
	public static int isOperand(String op) {
		switch (op.charAt(0)) {
		case '(':
			return 1;
		case ')':
			return 1;
		case '*':
			return 3;
		case '/':
			return 3;
		case '%':
			return 3;
		case '+':
			return 5;
		case '-':
			return 5;
		default:
			return 0;
		}
	}


	/**
	 * <PRE>
	 * ���ڿ��� ��, ��, �� ���� ����
	 * </PRE>
	 * @param String str : ���ڿ�
	 * @return String sRtn : ������ ���ŵ� ���ڿ�(�ݰ�, ���� ����)
	 */
	public static String removeSpaceAll(String str) {

		String sTemp = str;
		String sRtn = "";

		// �ݰ� �Ǵ� ���� ���鹮�� ����
		for (int i = 0; i < sTemp.length(); i++) {
			if (!" ".equals(sTemp.substring(i, i + 1))
					&& !" ".equals(sTemp.substring(i, i + 1))) {
				sRtn += sTemp.substring(i, i + 1);
			}
		}
		return sRtn;
	}


	/**
	 * <PRE>
	 * String(a,b,g)�� String(abc)�κ�ȯ�ϴ�  �޼ҵ�
	 * </PRE>
	 * @return removeStructur(s, DEFULT_COMMA)
	 * @param s
	 */
	public static String removeStructur(String s, String c) {
		StringBuffer sb = new StringBuffer();
		int i = 0;

		while (i < s.length() - c.length()) {
			if (!(s.substring(i, i + c.length()).equals(c))) {
				sb.append(String.valueOf(s.charAt(i)));
				i++;
			} else {
				i = i + c.length();
			}
		}
		sb.append(s.substring(i, s.length()));
		return sb.toString();
	}


	/**
	 * <PRE>
	 * String(a,b,g)�� String(a/b/c)�κ�ȯ�ϴ�  �޼ҵ�
	 * </PRE>
	 * @return removeStructur(s, DEFULT_COMMA)
	 * @param s, from,to
	 */
	public static String removeStructur(String s, String from, String to) {
		StringBuffer sb = new StringBuffer();
		int i = 0;

		while (i < s.length() - from.length()) {
			if (!(s.substring(i, i + from.length()).equals(from))) {
				sb.append(String.valueOf(s.charAt(i)));
				i++;
			} else {
				sb.append(to);
				i = i + from.length();
			}
		}
		sb.append(s.substring(i, s.length()));
		return sb.toString();
	}


	/**
	 * <PRE>
	 * String(s)�� �ڿ� int(leng)�� ������ŭ�����̽��� �־��ִ�  �޼ҵ�
	 * </PRE>
	 * @return fill(s, leng, " ")
	 * @param s
	 * @param leng
	 */
	public static String fillSpace(String s, int leng) {
		return fill(s, leng, " ");
	}


	/**
	 * <PRE>
	 * String(s)�� �ڿ� int(leng)�� ������ŭ 0�� �־��ִ�  �޼ҵ�
	 * </PRE>
	 * @return fill(s, leng, "0")
	 * @param s
	 * @param leng
	 */
	public static String fixZero(String s, int leng) {
		return fill(s, leng, "0");
	}


	/**
	 * <PRE>
	 * String(s)�� �ڿ� int(leng)�� ������ŭ String(c)�� �־��ִ�  �޼ҵ�
	 * </PRE>
	 * @return st.toString()
	 * @param s
	 * @param leng
	 * @param c
	 */
	public static String fill(String s, int leng, String c) {
		StringBuffer st = new StringBuffer();
		st.append(s);
		for (int i = s.length(); i < leng; i++) {
			st.append(c);
		}
		return st.toString();
	}


	/**
	 * <PRE>
	 * String(s)�� �տ� int(leng)�� ������ŭ �����̽��� �־��ִ�  �޼ҵ�
	 * </PRE>
	 * @return fillEnd(s, leng, " ")
	 * @param s
	 * @param leng
	 * @param c
	 */
	public static String fillEndSpace(String s, int leng) {
		return fillEnd(s, leng, " ");

	}


	/**
	 * <PRE>
	 * String(s)�� �տ� int(leng)�� ������ŭ 0�� �־��ִ�  �޼ҵ�
	 * </PRE>
	 * @return fillEnd(s, leng, "0")
	 * @param s
	 * @param leng
	 * @param c
	 */
	public static String fixEndZero(String s, int leng) {
		return fillEnd(s, leng, "0");

	}


	/**
	 * <PRE>
	 * String(s)�� �տ� int(leng)�� ������ŭ String(c)�� �־��ִ�  �޼ҵ�
	 * </PRE>
	 * @return st.toString()
	 * @param s
	 * @param leng
	 * @param c
	 */
	public static String fillEnd(String s, int leng, String c) {
		StringBuffer st = new StringBuffer();
		for (int i = s.length(); i < leng; i++) {
			st.append(c);
		}
		st.append(s);
		return st.toString();
	}


	/**
	 * <PRE>
	 * ������ �ֹε�Ϲ�ȣ�� ������ �������� �������� üũ �Ѵ�
	 * </PRE>
	 * @return st.toString()
	 * @param s
	 * @param leng
	 * @param c
	 */
	public static boolean isMan(String registry) {
		if (registry.substring(6, 7).equals("1") || registry.substring(6, 7).equals("3")) {
			return true;
		} else {
			return false;
		}
	}


	public static String getStackTrace(Throwable e) {
		java.io.ByteArrayOutputStream bos = new java.io.ByteArrayOutputStream();
		java.io.PrintWriter writer = new java.io.PrintWriter(bos);
		e.printStackTrace(writer);
		writer.flush();
		return bos.toString();
	}


	/**
	 * <PRE>
	 * ����(System TimeZone �� Locale ���� ) �⵵������ ��´�.
	 * </PRE>
	 * @param none
	 * @return String "yyyy" ������ ��Ʈ���� ��ȯ�Ѵ�.
	 */
	public static String getLocalYear() {
		Calendar calLocal = Calendar.getInstance();
		return "" + calLocal.get(Calendar.YEAR);
	}


	/**
	 * <PRE>
	 * ����(System TimeZone �� Locale ���� ) �⵵�������� ��´�.
	 * </PRE>
	 * @param none
	 * @return String "yyyyMM" ������ ��Ʈ���� ��ȯ�Ѵ�.
	 */
	public static String getLocalYearMonth() {
		Calendar calLocal = Calendar.getInstance();
		return "" + calLocal.get(Calendar.YEAR)
				+ makeTowDigit(calLocal.get(Calendar.MONTH) + 1);
	}


	/**
	 * <PRE>
	 * ����(System TimeZone �� Locale ���� ) �⵵�������� ��´�.
	 * </PRE>
	 * @param none
	 * @return String "yyyyMM" ������ ��Ʈ���� ��ȯ�Ѵ�.
	 */
	public static String getLocalMonth() {
		Calendar calLocal = Calendar.getInstance();
		return "" + makeTowDigit(calLocal.get(Calendar.MONTH) + 1);
	}


	/**
	 * <PRE>
	 * ����(System TimeZone �� Locale ���� ) �⵵�������� ��´�.
	 * </PRE>
	 * @param none
	 * @return String "yyyyMM" ������ ��Ʈ���� ��ȯ�Ѵ�.
	 */
	public static String getLocalDay() {
		Calendar calLocal = Calendar.getInstance();
		return "" + makeTowDigit(calLocal.get(Calendar.DATE));
	}


	/**
	 * <PRE>
	 * ����(System TimeZone �� Locale ���� ) ��¥������ ��´�.
	 * </PRE>
	 * @param none
	 * @return String "yyyyMMdd" ������ ��Ʈ���� ��ȯ�Ѵ�.
	 */
	public static String getLocalDate() {
		Calendar calLocal = Calendar.getInstance();
		return "" + calLocal.get(Calendar.YEAR)
				+ makeTowDigit(calLocal.get(Calendar.MONTH) + 1)
				+ makeTowDigit(calLocal.get(Calendar.DATE));
	}


	/**
	 * <PRE>
	 * ����(System TimeZone �� Locale ���� ) ��¥������ ��´�.
	 * </PRE>
	 * @param none
	 * @return String "yyyy-MM-dd" ������ ��Ʈ���� ��ȯ�Ѵ�.
	 */
	public static String getLocalDateQue() {
		Calendar calLocal = Calendar.getInstance();
		return "" + calLocal.get(Calendar.YEAR) +"-"
		+ makeTowDigit(calLocal.get(Calendar.MONTH) + 1) +"-"
		+ makeTowDigit(calLocal.get(Calendar.DATE));
	}


	/**
	 * <PRE>
	 * �������� From ~ To �� �ش��ϴ��� ����
	 * </PRE>
	 * @param yyyymmdd ~ yyyymmdd ������ �Ⱓ�� �ش��ϴ��� ����
	 * @return true / false
	 */
	public static boolean isOnDate(String yyyymmddFrom, String yyyymmddTo) {
		boolean result = false;

		if (yyyymmddFrom == null || yyyymmddFrom.equals(""))
			return result;
		if (yyyymmddTo == null || yyyymmddTo.equals(""))
			return result;

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date today = sdf.parse(getLocalDate());
			Date from = sdf.parse(yyyymmddFrom);
			Date to = sdf.parse(yyyymmddTo);

			if (today.compareTo(from) >= 0 && today.compareTo(to) <= 0) {
				result = true;
			} else {
				result = false;
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		}
	}


	/**
	 * <PRE>
	 * ����(System TimeZone �� Locale ���� ) ��¥/�ð������� ��´�.
	 * </PRE>
	 * @param none
	 * @return String "yyyyMMddHHmmss" ������ ��Ʈ���� ��ȯ�Ѵ�.
	 */
	public static String getLocalDateTime() {
		Calendar calLocal = Calendar.getInstance();
		return "" + calLocal.get(Calendar.YEAR)
				+ makeTowDigit(calLocal.get(Calendar.MONTH) + 1)
				+ makeTowDigit(calLocal.get(Calendar.DATE))
				+ makeTowDigit(calLocal.get(Calendar.HOUR_OF_DAY))
				+ makeTowDigit(calLocal.get(Calendar.MINUTE))
				+ makeTowDigit(calLocal.get(Calendar.SECOND));
	}
	/**
	 * <PRE>
	 * ����(System TimeZone �� Locale ���� ) ��¥������ ��´�.
	 * </PRE>
	 * @param none
	 * @return String "yyyy str MM str dd" ������ ��Ʈ���� ��ȯ�Ѵ�.
	 */
	public static String getLocalDate(String str) {

		if(!"".equals(nvl(str))){
			Calendar calLocal = Calendar.getInstance();
			return "" + calLocal.get(Calendar.YEAR)
					+ str + makeTowDigit(calLocal.get(Calendar.MONTH) + 1)
					+ str + makeTowDigit(calLocal.get(Calendar.DATE));
		}else{
			return getLocalDate();
		}

	}

	/**
	 * <PRE>
	 * ����(System TimeZone �� Locale ���� ) ��¥/�ð������� ��´�.
	 * </PRE>
	 * @param none
	 * @return String "HHmmss" ������ ��Ʈ���� ��ȯ�Ѵ�.
	 */
	public static String getLocalTime() {
		Calendar calLocal = Calendar.getInstance();
		return "" + makeTowDigit(calLocal.get(Calendar.HOUR_OF_DAY))
				+ makeTowDigit(calLocal.get(Calendar.MINUTE))
				+ makeTowDigit(calLocal.get(Calendar.SECOND));
	}


	/**
	 * <PRE>
	 * ���� �ý��� �ð����� Ư���� add��ŭ ���Ѵ�.
	 * </PRE>
	 * @param java.lang.String pattern "yyyy, MM, dd, HH, mm, ss and more"
	 * @param int add(�߰��ϰų� �� �Ⱓ)
	 * @return ���� �ý��� �ð� String
	 */
	public static String getCalendarMonth(String pattern, int add) {
		Calendar curr = Calendar.getInstance();
		curr.add(Calendar.MONTH, add);
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(pattern);

		return formatter.format(curr.getTime());
	}


	/**
	 * <PRE>
	 * ���� �ý��� �ð����� Ư���⵵�� add��ŭ ���Ѵ�.
	 * </PRE>
	 * @param java.lang.String pattern "yyyy, MM, dd, HH, mm, ss and more"
	 * @param int add(�߰��ϰų� �� �Ⱓ)
	 * @return ���� �ý��� �ð� String
	 */
	public static String getCalendarYear(String pattern, int add) {
		Calendar curr = Calendar.getInstance();
		curr.add(Calendar.YEAR, add);
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(pattern);

		return formatter.format(curr.getTime());
	}


	/**
	 * <PRE>
	 * �Է¹��� ��¥ ���� Ư���� add��ŭ ���Ѵ�.
	 * </PRE>
	 * @param java.lang.String pattern "yyyy, MM, dd, HH, mm, ss and more"
	 * @param int add(�߰��ϰų� �� �Ⱓ)
	 * @return ���� �ý��� �ð� String
	 */
	public static String getAddDate(String effdate, String pattern, int add) {
		Calendar curr = Calendar.getInstance();
		String y = substring(effdate, 0, 4);
		String m = substring(effdate, 4, 6);
		String d = substring(effdate, 6);

		curr.set(Integer.parseInt(y), Integer.parseInt(m) - 1, Integer.parseInt(d));
		curr.add(Calendar.DATE, add);
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(pattern);

		return formatter.format(curr.getTime());
	}


	/**
	 * <PRE>
	 * �ð� ��Ʈ���� Ư���������� ��ȯ�Ͽ� ��ȯ�Ѵ�.
	 * </PRE>
	 * @param strDate "yyyyMMddHHmmss" ������ ���ڿ�
	 * @return String "yyyy/MM/dd HH:mm:ss" ������ ��Ʈ���� ��ȯ�Ѵ�.
	 */
	public static String convertDateTime(String strDate) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = sdf.parse(strDate);

			return toStr(date, "ymdhms");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}


	/**
	 * <PRE>
	 * �ð� ��Ʈ���� Ư���������� ��ȯ�Ͽ� ��ȯ�Ѵ�.
	 * </PRE>
	 * @param strDate "yyyyMMddHHmmss" ������ ���ڿ�
	 * @param formatStr ��ȯ�� ���� ���ڿ�
	 * @return String "yyyy/MM/dd HH:mm:ss" ������ ��Ʈ���� ��ȯ�Ѵ�.
	 */
	public static String convertDateTime(String strDate, String formatStr) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = sdf.parse(strDate);

			return toStr(date, "ymdhms", formatStr);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}


	/**
	 * <PRE>
	 * �ð� ��Ʈ���� Default����(yyyy-MM-dd)���� ��ȯ�Ͽ� ��ȯ�Ѵ�.
	 * </PRE>
	 * @param strDate "yyyyMMdd" ������ ���ڿ�
	 * @return String Ư���� ������ ��Ʈ���� ��ȯ�Ѵ�.
	 */
	public static String convertDate(String strDate) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date date = sdf.parse(strDate);

			return toStr(date);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}


	/**
	 * <PRE>
	 * �ð� ��Ʈ���� Ư���������� ��ȯ�Ͽ� ��ȯ�Ѵ�.
	 * </PRE>
	 * @param strDate "yyyyMMdd" ������ ���ڿ�
	 * @param formatStr ��ȯ�� ���� ���ڿ�
	 * @return String Ư���� ������ ��Ʈ���� ��ȯ�Ѵ�.
	 */
	public static String convertDate(String strDate, String formatStr) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date date = sdf.parse(strDate);

			return toStr(date, "ymd", formatStr);

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}


	public static String convertTime(String strDate) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
			Date date = sdf.parse(strDate);

			return toStr(date);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}


	public static String convertTime(String strTime, String formatStr) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
			Date date = sdf.parse(strTime);

			return toStr(date, "hms", formatStr);

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}


	/**
	 * <PRE>
	 * �������� ��ȯ�Ѵ�.
	 * </PRE>
	 * @param cDate "yyyyMM" ������ ���ڿ�
	 * @return String �������� ����Ͽ� ��Ʈ��(yyyyMM)���� ��ȯ�Ѵ�.
	 */
	public static String getBeforeMonth(String cDate) {
		try {
			String rtnDate = "";

			int year = Integer.parseInt(cDate.substring(0, 4));
			int month = Integer.parseInt(cDate.substring(4, 6));

			String strYear = cDate.substring(0, 4);
			String strMonth = cDate.substring(4, 6);

			if (strMonth.equals("01")) {
				strMonth = "12";
				year--;
				if (year < 10) {
					rtnDate = "000" + year + strMonth;
				} else if (year < 100) {
					rtnDate = "00" + year + strMonth;
				} else if (year < 100) {
					rtnDate = "0" + year + strMonth;
				} else {
					rtnDate = year + strMonth;
				}
			} else {
				month--;
				if (month < 10) {
					rtnDate = strYear + "0" + month;
				} else {
					rtnDate = strYear + month;
				}
			}
			return rtnDate;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}


	/**
	 * <PRE>
	 * �������� ��ȯ�Ѵ�.
	 * </PRE>
	 *
	 * @param cDate "yyyyMM" ������ ���ڿ�
	 * @return String �������� ����Ͽ� ��Ʈ��(yyyyMM)���� ��ȯ�Ѵ�.
	 */
	public static String getAfterMonth(String cDate) {
		try {
			String rtnDate = "";

			int year = Integer.parseInt(cDate.substring(0, 4));
			int month = Integer.parseInt(cDate.substring(4, 6));

			String strYear = cDate.substring(0, 4);
			String strMonth = cDate.substring(4, 6);

			if (strMonth.equals("12")) {
				strMonth = "01";
				year++;
				if (year < 10) {
					rtnDate = "000" + year + strMonth;
				} else if (year < 100) {
					rtnDate = "00" + year + strMonth;
				} else if (year < 100) {
					rtnDate = "0" + year + strMonth;
				} else {
					rtnDate = year + strMonth;
				}
			} else {
				month++;
				if (month < 10) {
					rtnDate = strYear + "0" + month;
				} else {
					rtnDate = strYear + month;
				}
			}
			return rtnDate;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}


	/**
	 * <PRE>
	 * date�� �ش� ������ ���ϱ�
	 * </PRE>
	 * @param from date. 'YYYYMMDD' �����̴�.
	 * @param offset
	 * @return to date. 'YYYYMMDD' �����̴�.
	 * @exception java.text.ParseException
	 */
	public static String getLastYmd(String fromDt) throws java.text.ParseException {
		String nextMonthFirstYmd = addDate(fromDt, Calendar.MONTH, 1).substring(0, 6) + "01";

		return addDate(nextMonthFirstYmd, Calendar.DATE, -1);
	}


	/**
	 * <PRE>
	 * from date ���� offset ��ŭ ���� ���� �����Ѵ�.
	 * </PRE>
	 * @param from date. 'YYYYMMDD' �����̴�.
	 * @param offset.
	 * @return to date. 'YYYYMMDD' �����̴�.
	 * @exception java.text.ParseException
	 */
	public static String addDate(String fromDt, int offset) throws java.text.ParseException {
		return addDate(fromDt, Calendar.DATE, offset);
	}


	/**
	 * <PRE>
	 * from date���� field �� offset ��ŭ ������ to date�� �����Ѵ�.
	 * </PRE>
	 * @param from date. 'YYYYMMDD' �����̴�.
	 * @param field.
	 * @param offset.
	 * @return to date. 'YYYYMMDD' �����̴�.
	 * @exception java.text.ParseException
	 */
	public static String addDate(String fromDt, int field, int offset) throws java.text.ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
		Date fromDate = sdf.parse(fromDt);
		Calendar cal = Calendar.getInstance();
		cal.setTime(fromDate);
		cal.add(field, offset);

		Date toDate = cal.getTime();

		return sdf.format(toDate);
	}


	/**
	 * <PRE>
	 * ���ڸ� ���ڿ��� ��ȯ�ϴµ�, 2�ڸ��� �̸��̸� ���ڸ����� �����.
	 * </PRE>
	 * @param int num
	 * @return String "00" ������ ��Ʈ���� ��ȯ�Ѵ�.
	 */
	protected static String makeTowDigit(int num) {
		return (num < 10 ? "0" : "") + num;
	}


	/**
	 * <PRE>
	 * YYYYMMDD ��¥������ ������ g �� ������ ��ȯ�Ѵ�.(�� putDateGubn('20001031','/') => 2000/10/31
	 * </PRE>
	 * @param String s, String g
	 * @return String
	 */
	public static String putDateGubn(String s, String g) {
		return (s.length() == 8) ? s.substring(0, 4) + g + s.substring(4, 6) + g + s.substring(6, 8) : s;
	}


	/**
	 * <PRE>
	 * ��¥������ �����ڸ� ���� ��ȯ�Ѵ�.(�� delDateGubn('2000/10/31') => 20001031
	 * </PRE>
	 * @param String s
	 * @return String
	 */
	public static String delDateGubn(String s) {
		return (s.length() == 10) ? s.substring(0, 4) + s.substring(5, 7)
				+ s.substring(8, 10) : s;
	}


	/**
	 * <PRE>
	 * currentTime���� �Ѿ�� String(hh:mm)�� �ð��� intervalTime���� �Ѿ�� String(hh:mm)�� �ð����� �� �ð��� ���̸� ����ϴ� �޼ҵ�
	 * </PRE>
	 * @param currentTime
	 * @param intervalTime
	 */
	public static double getBetweenTime(String currentTime, String intervalTime) {
		int hh1 = 0, mm1 = 0;
		int hh2 = 0, mm2 = 0;
		double d_hh = 0, d_mm = 0, interval_time = 0;

		hh1 = Integer.parseInt(currentTime.substring(0, 2));
		mm1 = Integer.parseInt(currentTime.substring(3, 5));

		hh2 = Integer.parseInt(intervalTime.substring(0, 2));
		mm2 = Integer.parseInt(intervalTime.substring(3, 5));

		d_hh = hh2 - hh1;
		d_mm = mm2 - mm1;

		if (d_hh < 0) {
			d_hh = 24 + d_hh;
		}
		if (d_mm >= 0) {
			d_mm = d_mm / 60;
		} else {
			d_hh = d_hh - 1;
			d_mm = (60 + d_mm) / 60;
		}
		interval_time = d_hh + d_mm;

		return interval_time;
	}


	/**
	 * <PRE>
	 * �ش� ���� �������� �ƴ����� �Ǻ��Ѵ�
	 * </PRE>
	 * @param yyyy
	 * @param mm
	 * @param dd
	 * @return boolean
	 */
	public static boolean isDate(int yyyy, int mm, int dd) {

		int nMonth[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int yMonth[] = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		if (mm <= 0 || mm > 12){
			return false;
		}

		if ((yyyy % 4) == 0 && ((yyyy % 100) != 0 || (yyyy % 400) == 0)) { // �����϶�
			if (dd > yMonth[mm - 1]){
				return false;
			}
		} else {
			if (dd > nMonth[mm - 1]){
				return false;
			}
		}
		return true;
	}


	/**
	 * <PRE>
	 * 0000��00��00�� ���·� ���� ���� �Ѵ�
	 * </PRE>
	 * @param dateStr
	 * @return String
	 */
	public static String getDateKo(String dateStr) {

		if (CommonUtil.isNull(dateStr)){
			return "";
		}

		int year = Integer.parseInt(dateStr.substring(0, 4));
		int month = Integer.parseInt(dateStr.substring(4, 6));
		int day = Integer.parseInt(dateStr.substring(6, 8));

		dateStr = year + "��" + month + "��" + day + "��";
		return dateStr;
	}


	/**
	 * <PRE>
	 * �ݿø�,����,�ø� ���� �Լ����� ���������� ȣ���Ѵ�
	 * </PRE>
	 * @param num
	 * @return
	 */
	public static String doubleToString(double num) {
		java.text.DecimalFormat df = new java.text.DecimalFormat("####.####");
		return df.format(num).toString();
	}


	/**
	 * <PRE>
	 * �״��� �ִ� ����
	 * </PRE>
	 * @param String date
	 * @return String
	 * @exception
	 * @see
	 */
	public static String getMax_Date(String date) { // �״��� �ִ� ����
		GregorianCalendar cal = new GregorianCalendar();
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(4, 6));
		String max_day = "";
		if (month == 2) {
			boolean yun = cal.isLeapYear(year);
			if (yun) // ����
				max_day = "29";
			else
				max_day = "28";
		} else {
			if (month == 4 || month == 6 || month == 9 || month == 11)
				max_day = "30";
			else
				max_day = "31";
		}
		return max_day;

	}


	/**
	 * <PRE>
	 * �����ϰ� ���������� �����ָ� �ش��Ͽ� ���� ���̸� ���� �ݴϴ�
	 * </PRE>
	 * @param
	 * @param
	 * @return long
	 * @exception
	 * @see
	 */
	public static long dayDifference(String startDay, String endDay) {
		long result = 0;
		long diffMillis = 0;

		Calendar todayCal;
		Calendar saleStrDmCal;
		try {
			Date sDay = new SimpleDateFormat("yyyyMMdd").parse(startDay.replaceAll("-", ""));
			todayCal = new GregorianCalendar();
			todayCal.setTime(sDay);

			Date eDay = new SimpleDateFormat("yyyyMMdd").parse(endDay.replaceAll("-", ""));
			saleStrDmCal = new GregorianCalendar();
			saleStrDmCal.setTime(eDay);

			diffMillis = todayCal.getTimeInMillis() - saleStrDmCal.getTimeInMillis();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result = diffMillis / (24 * 60 * 60 * 1000);

		return result;
	}


	/**
	 * <PRE>
	 * yyyymmdd��  ���� �� �ָ� ������ ���� �ݴϴ�
	 * </PRE>
	 * @param
	 * @param
	 * @return String
	 * @exception
	 * @see
	 */
	public static String toDayWeek(String value, String lang) {
		value = value.replaceAll("-", "");
		value = value.replaceAll("/", "");

		String weekstr = "";
		if (value.length() == 8) {
			Calendar cal = new GregorianCalendar();

			cal.set(Calendar.YEAR, Integer.parseInt(value.substring(0, 4)));
			cal.set(Calendar.MONTH, Integer.parseInt(value.substring(4, 6)) - 1);
			cal.set(Calendar.DAY_OF_MONTH,
					Integer.parseInt(value.substring(6, 8)));

			int seq = cal.get(Calendar.DAY_OF_WEEK);
			String[] week = new String[8];
			if (lang.toLowerCase().startsWith("ko")) {
				week[0] = "";
				week[1] = "��";
				week[2] = "��";
				week[3] = "ȭ";
				week[4] = "��";
				week[5] = "��";
				week[6] = "��";
				week[7] = "��";
				if (seq == 1) {
					weekstr = "<b><font color=red>(" + week[seq]
							+ "����)</font></b>";
				} else {
					weekstr = "<b>(" + week[seq] + "����)</b>";
				}
			} else if (lang.toLowerCase().startsWith("en")) {
				week[0] = "";
				week[1] = "Sun";
				week[2] = "Mon";
				week[3] = "Tue";
				week[4] = "Wed";
				week[5] = "Thu";
				week[6] = "Fri";
				week[7] = "Sat";
				if (seq == 1) {
					weekstr = "<b><font color=red>(" + week[seq]
							+ ")</font></b>";
				} else {
					weekstr = "<b>(" + week[seq] + ")</b>";
				}
			}
		}
		return weekstr;
	}


	/**
	 * <PRE>
	 * Entity Class�� null string field �ʱ�ȭ.
	 * </PRE>
	 * @param java .lang.Object Object���� public java.lang.String ���� member variable���� ������ �ش�.
	 */
	public static void fixNull(Object o) {
		if (o == null)
			return;

		Class c = o.getClass();
		if (c.isPrimitive()){
			return;
		}

		Field[] fields = c.getFields();
		for (int i = 0; i < fields.length; i++) {
			try {
				Object f = fields[i].get(o);
				Class fc = fields[i].getType();

				if (fc.getName().equals("java.lang.String")) {
					if (f == null)
						fields[i].set(o, "");
					else
						fields[i].set(o, f);
				}
			} catch (Exception e) {
			}
		}
	}


	/**
	 * <PRE>
	 * Entity Class�� null string field �ʱ�ȭ &amp; trim().
	 * </PRE>
	 * @param java.lang.Object Object���� public java.lang.String ���� member variable���� ������ �ش�.
	 */
	public static void fixNullAndTrim(Object o) {
		if (o == null){
			return;
		}
		Class c = o.getClass();
		if (c.isPrimitive())
			return;

		Field[] fields = c.getFields();
		for (int i = 0; i < fields.length; i++) {
			try {
				Object f = fields[i].get(o);
				Class fc = fields[i].getType();
				if (fc.getName().equals("java.lang.String")) {
					if (f == null)
						fields[i].set(o, "");
					else {
						String item = ((String) f).trim();
						fields[i].set(o, item);
					}
				}
			} catch (Exception e) {
			}
		}
	}


	/**
	 * <PRE>
	 * �ش� ���ڿ� Check ó��
	 * </PRE>
	 * @param s_value �� ���ڿ�
	 * @return boolean true or false
	 */
	public static boolean isNull(String s_value) {
		boolean is_null = false;

		if (s_value == null || s_value.trim().equals("")) {
			is_null = true;
		}

		return is_null;
	}


	/**
	 * <PRE>
	 * Null Check initValue : ���� ��� �ʱⰪ
	 * </PRE>
	 * @param value
	 * @param initValue
	 * @return retValue
	 */
	public static String getNvl(String value, String initValue) {

		String retValue = "";

		if (CommonUtil.isNull(value)) {
			retValue = initValue;
		} else {
			retValue = value;
		}
		return retValue;
	}


	/**
	 * <PRE>
	 * 8859_1 --> KSC5601.
	 * </PRE>
	 * @return KSC5601
	 * @param english : 8859_1
	 */
	public static String E2K(String english) {
		String korean = null;

		if (english == null)
			return null;

		try {
			korean = new String(new String(english.getBytes("8859_1"), "KSC5601"));
		} catch (UnsupportedEncodingException e) {
			korean = new String(english);
		}
		return korean;
	}


	/**
	 * <PRE>
	 * KSC5601 --> 8859_1.
	 * <PRE>
	 * @return 8859_1
	 * @param korean : KSC5601
	 */
	public static String K2E(String korean) {
		String english = null;

		if (korean == null)
			return null;

		english = new String(korean);
		try {
			english = new String(new String(korean.getBytes("KSC5601"),
					"8859_1"));
		} catch (UnsupportedEncodingException e) {
			english = new String(korean);
		}
		return english;
	}


	/**
	 * <PRE>
	 * üũ�ڽ��� ���� üũ�Ͽ� check ���θ� �����Ѵ�.
	 * </PRE>
	 * @param chkObj
	 * @param index
	 * @return
	 */
	public static String getCheckBoxIndex(String[] chkObj, String value) {
		String checked = "";

		if (chkObj != null) {
			for (int i = 0; i < chkObj.length; i++) {
				if (value.equals(chkObj[i])) {
					checked = "checked";
				}
			}
		}
		return checked;
	}


	/**
	 * <PRE>
	 * ��� ���ڿ��� ����(split)�� ���ڸ� �������� �����Ͽ� index ��°�� �ش�Ǵ� ���ڿ��� ��ȯ�Ѵ�.
	 * </PRE>
	 * @param src
	 * @param split
	 * @param index
	 * @return
	 */
	public static String getSplitString(String src, String split, int index) {

		String[] strTmp = src.split(split);
		if (strTmp.length < index + 1)
			return "";

		return strTmp[index];
	}


	/**
	 * <PRE>
	 * ���ڸ� �־��� Format�� �µ��� ��ȯ�Ͽ� ��ȯ
	 * </PRE>
	 * @param src
	 * @return
	 */
	public static String getNumberFormat(String src, String formmatStr) {
		NumberFormat formatter = new DecimalFormat(formmatStr);

		return formatter.format(Double.parseDouble(src));
	}


	public static String getNumberFormat(String src) {
		String tmp = "";

		if ((src != null) && (src.endsWith("-") || src.startsWith("-"))) {
			tmp = "-";
			src = src.replace("-", "");
		}
		src = src.trim();
		if (isNumeric(src)) {
			return tmp + getNumberFormat(src, "#,###.#######");
		} else {
			return "0";
		}

	}


	public static String getNumberFormat(double src, String formmatStr) {
		NumberFormat formatter = new DecimalFormat(formmatStr);

		return formatter.format(src);
	}


	/**
	 * <PRE>
	 *  ���ڿ� ���ʿ� '0'�� �����Ѵ�
	 * </PRE>
	 * @param
	 * @return String
	 * @exception
	 * @see
	 */

	public static String RemoveZero(String str) {
		try {
			for (int i = 0; i < str.length(); i++) {
				if (!(str.substring(i, i + 1).indexOf("0") > -1)) {
					return str.substring(i);
				}
			}
		} catch (IndexOutOfBoundsException e) {
			str = "";
		}
		return str;
	}


	/**
	 * <PRE>
	 * Double ����  int�� �ٲٰ�.. �װ� String ���� �����ش�
	 * </PRE>
	 * @param
	 * @return String
	 * @exception
	 * @see
	 */
	public static String ConvertDoubleIntStr(String str) {
		if (str == null || str.equals("")) {
			return "";
		} else {
			return toStr((int) Double.parseDouble(str));
		}
	}


	/**
	 * <PRE>
	 * ���ڿ��� ������ ���ڿ��� ã�Ƽ� ���ο� ���ڿ��� �ٲٴ� �Լ� origianl ��� ���ڿ� oldstr ã�� ���ڿ� newstr �ٲ� ���ڿ� return �ٲ� ���
	 * </PRE>
	 */
	public static String namoReplace(String original, String oldstr,
			String newstr) {
		String convert = new String();
		int pos = 0;
		int begin = 0;
		pos = original.indexOf(oldstr);

		if (pos == -1)
			return original;

		while (pos != -1) {
			convert = convert + original.substring(begin, pos) + newstr;
			begin = pos + oldstr.length();
			pos = original.indexOf(oldstr, begin);
		}
		convert = convert + original.substring(begin);

		return convert;
	}


	/**
	 * <PRE>
	 * ������ HTML ������ȣ�� ���ڸ� HTML Ư����ȣ �������� ��ȯ�մϴ�. htmlstr �ٲ� ����� ���ڿ� return �ٲ� ���
	 * </PRE>
	 */
	public static String namoConvertHtmlchars(String htmlstr) {
		String convert = new String();
		convert = namoReplace(htmlstr, "<", "&lt;");
		convert = namoReplace(convert, ">", "&gt;");
		convert = namoReplace(convert, "\"", "&quot;");
		convert = namoReplace(convert, "&nbsp;", "&amp;nbsp;");
		return convert;
	}


	/**
	 * <PRE>
	 * ���ڿ��� ���� ���������� �Ǵ��Ѵ�.
	 * </PRE>
	 *
	 * @param str �˻��� ���ڿ�
	 * @return ���ڸ� true, ���ڰ� �ƴϸ� false�� ��ȯ�Ѵ�.
	 */
	public static boolean isNumeric(String str) {
		if (str == null)
			return false;
		char[] ch = str.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			if (!(ch[i] >= '0' && ch[i] <= '9') && (ch[i] != '.')) {
				return false;
			}
		}
		return true;
	}


	/**
	 * <PRE>
	 * ������ ������ �޾Ƽ� ������ ������ ���� ���� ����Ʈ�� ����ϴ�~!
	 * </PRE>
	 */
	public static String makeOrghList(HashMap hm, String orgh) {
		String list = orgh;
		for (int i = 0; i < hm.size(); i++) {

			String[] list1 = list.split("_");
			// �����Ͱ� �ִ��� Ȯ���� �ϰ�
			if (hm.containsKey(list1[0])){
				// �ش� �����Ͱ� �ֻ��� ���������� Ȯ�� �Ѵ�
				if (Integer.parseInt((String) hm.get(list1[0])) != 0) {
					list = hm.get(list1[0]) + "_" + list;
					continue;
				} else {
					break;
				}
			}
		}
		return list;
	}

	/**
	 *�Ҽ��� ���� .00 ��ġ��
	 * @param value
	 * @return
	 */
	public static String RatesView(String value) {

		String retValue = "";
		if(value.equals("0") || value.equals("0.0")){
			retValue = "-";
		}else{
			int pos = value.lastIndexOf( "." );
			String strlen = value.substring(pos+1, value.length());

			if(strlen.length() <= 0){
				retValue = value + ".00";
			}else{
				String[] svalue = value.split("\\.");
				if(svalue[1].length() == 1){
					retValue = svalue[0] + "." + svalue[1] + "0"; 
				}else{
					retValue = svalue[0] + "." + svalue[1];
				}
			}
		}
		return retValue;
	}	


	/**
	 * <PRE>
	 * HTML�� Ư����ȣ���� ����ȯ�Ѵ�.
	 * </PRE>
	 * &amp; -> &amp;amp;
	 * &quot; -> &amp;quot;
	 * &lt; -> &amp;lt;
	 * &gt; -> &amp;gt;
	 * @param str ��ȯ�� ���ڿ�
	 * @return ��ȯ�� ���ڿ�
	 */
	public static String htmlToChars(String str) {
		String rtnStr = "";
		if (str == null || "".equals(str)){
			return rtnStr;
		}
		rtnStr = charsToHtml(str);

		rtnStr = replaceAllString(rtnStr, "&", "&amp;");
		rtnStr = replaceAllString(rtnStr, "<", "&lt;");
		rtnStr = replaceAllString(rtnStr, ">", "&gt;");
		rtnStr = replaceAllString(rtnStr, "#", "&#35;");
		rtnStr = replaceAllString(rtnStr, "\'", "&#39;");
		rtnStr = replaceAllString(rtnStr, "\"", "&quot;");

		StringBuffer sb = new StringBuffer(rtnStr);
		char ch;

		for (int i = 0; i < sb.length(); i++) {
			ch = sb.charAt(i);
			if (ch == '(') {
				sb.replace(i, i + 1, "&#40;");
				i += 4;
			} else if (ch == ')') {
				sb.replace(i, i + 1, "&#41;");
				i += 4;
			}
		}
		return sb.toString();
	}
	/**
	 * <PRE>
	 * HTML�� Ư����ȣ���� ����ȯ�Ѵ�.
	 * </PRE>
	 * &amp; -> &amp;amp;
	 * &quot; -> &amp;quot;
	 * &lt; -> &amp;lt;
	 * &gt; -> &amp;gt;
	 * @param str ��ȯ�� ���ڿ�
	 * @return ��ȯ�� ���ڿ�
	 */
	public static String charsToHtml(String str) {
		String rtnStr = "";
		if (str == null || "".equals(str)){
			return rtnStr;
		}
		rtnStr = str;

		rtnStr = replaceAllString(rtnStr, "&amp;", "&");
		rtnStr = replaceAllString(rtnStr, "&lt;", "<");
		rtnStr = replaceAllString(rtnStr, "&gt;", ">");
		rtnStr = replaceAllString(rtnStr, "&#35;", "#");
		rtnStr = replaceAllString(rtnStr, "&#39;", "`");
		rtnStr = replaceAllString(rtnStr, "&quot;", "\"");
		rtnStr = replaceAllString(rtnStr, "&#40;", "(");
		rtnStr = replaceAllString(rtnStr, "&#41;", ")");

		return rtnStr;
	}

	/**
	 * <pre>
	 * ���ڿ����� �־��� separator �� �ɰ��� ���ڹ迭�� �����Ѵ�.
	 * ���������� StringTokenizer�� ����ϹǷ� separator�� delimiter��� �����ϸ�
	 * seperator�� �ߺ��Ǵ� ��� ���ڿ��� return �迭�� ���Ե��� �ʴ´�.
	 * ��) str ==> foo|+:bo++o
	 *     separator ==> |+:
	 *     return ==> {"foo", "bo", "o")
	 *
	 *</pre>
	 *
	 * @param str �������ڿ�
	 * @param separator ���ϴ� token ���ڿ�
	 * @return ��Ʈ���迭
	 */

	public static String[] split(String str, String separator)
	{
		StringTokenizer st = new StringTokenizer(str, separator);
		String[] values = new String[st.countTokens()];
		int pos = 0;
		while (st.hasMoreTokens())
		{
			values[pos++] = st.nextToken();
		}

		return values;
	}

	public static String arrayJoin(String glue, String array[]) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			result.append(array[i]);
			if (i < array.length - 1) result.append(glue);
		}
		result.trimToSize();
		return result.toString();
	}
	public static String randomString(int length){
		Random rnd =new Random();

		StringBuffer str =new StringBuffer();

		for(int i=0;i<length;i++){
			if(rnd.nextBoolean()){
				str.append(String.valueOf((char)((int)(rnd.nextInt(26))+97)).toLowerCase());
			}else{
				str.append((rnd.nextInt(10)));
			}
		}
		return str.toString();
	}
	public static String changeHyperLinkText(String str){
		String regex = "([\\p{Alnum}]+)://([a-z0-9.\\-&/%=?:@#$(),.+;~\\_]+)";
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(str);
		String s = m.replaceAll("<a href='http://$2' target='_blank'>http://$2</a>");
		return s;
	}
	public static boolean checkHan(char cValue) {
		if (cValue >= 0xAC00 && cValue <= 0xD743) {
			return true;
		} else {
			return false;
		}
	}
	public static boolean checkHanString(String str){
		for(int i=0; i<str.length(); i++){
			if(checkHan(str.charAt(i))){
				return true;
			}
		}
		return false;
	}

	/**
	 * ���ڿ��� �ѱ�,������ �ѱ��ڶ� �߰ߵǸ� true
	 * @param str
	 * @return
	 */
	public static boolean checkHanEngCheck(String str){
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if ((0xAC00 <= c && c <= 0xD7A3) || (0x3131 <= c && c <= 0x318E)) {
				return true;
			} else if ((0x61 <= c && c <= 0x7A) || (0x41 <= c && c <= 0x5A)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * ù��° ���ڿ� üũ
	 * @param str
	 * @return
	 */
	public static boolean checkHanEngCheck2(String str){
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(0);
			if ((0xAC00 <= c && c <= 0xD7A3) || (0x3131 <= c && c <= 0x318E)) {
				return true;
			} else if ((0x61 <= c && c <= 0x7A) || (0x41 <= c && c <= 0x5A)) {
				return true;
			}
		}
		return false;
	}

	public static String convertPubdateToRssFormat(String pubdate) throws ParseException {
		Date time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(pubdate);
		String rssFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss +0900", Locale.ENGLISH).format(time);
		return rssFormat;
	}

	/**
	 * ������
	 * @param str
	 * @param length
	 * @return
	 */
	public static String reducingText(String str,Integer length){
		if(str.length() > length)     return str.substring(0,length)+"...";
		else return str;
	}

	public static void setSession(String key,Object value){
		if(RequestContextHolder.getRequestAttributes() != null )
			RequestContextHolder.getRequestAttributes().setAttribute(key,value,RequestAttributes.SCOPE_SESSION);
	}
	public static Object getSession(String key){
		if(RequestContextHolder.getRequestAttributes() == null ) return null;
		else return (Object)RequestContextHolder.getRequestAttributes().getAttribute(key,RequestAttributes.SCOPE_SESSION);
	}
	public static long diffOfDate(String end) throws Exception
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		Date beginDate = formatter.parse(end);
		Date endDate = formatter.parse(getLocalDateQue());

		long diff = endDate.getTime() - beginDate.getTime();
		long diffDays = diff / (24 * 60 * 60 * 1000);

		return diffDays;
	}

	/**
	 * �������� �ݿø�
	 * @param dNumber
	 * @param intNumDigits
	 * @return
	 */
	public static double excelRound(Double dNumber, Integer intNumDigits) {
		return Double.parseDouble(String.format("%." + Integer.toString(intNumDigits) + "f", dNumber));
	}
	public static double ceil(Double dNumber){
		return Math.ceil(dNumber);
	}
	public static String getText(String content) {
		Pattern SCRIPTS = Pattern.compile("<(no)?script[^>]*>.*?</(no)?script>",Pattern.DOTALL);
		Pattern STYLE = Pattern.compile("<style[^>]*>.*</style>",Pattern.DOTALL);
		Pattern TAGS = Pattern.compile("<(\"[^\"]*\"|\'[^\']*\'|[^\'\">])*>");
		Pattern nTAGS = Pattern.compile("<\\w+\\s+[^<]*\\s*>");
		Pattern ENTITY_REFS = Pattern.compile("&[^;]+;");
		Pattern WHITESPACE = Pattern.compile("\\s\\s+");

		Matcher m;

		m = SCRIPTS.matcher(content);
		content = m.replaceAll("");
		m = STYLE.matcher(content);
		content = m.replaceAll("");
		m = TAGS.matcher(content);
		content = m.replaceAll("");
		m = ENTITY_REFS.matcher(content);
		content = m.replaceAll("");
		m = WHITESPACE.matcher(content);
		content = m.replaceAll(" ");          

		return content;
	}

	public static String htmlTochar2(String str){
		String pattern = "<(\\/?)(?!\\/####)([^<|>]+)?>";

		String[] allowTags = "img,br,p".split(",");

		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < allowTags.length; i++)
		{
			buffer.append("|" + allowTags[i].trim() + "(?!\\w)");
		}

		pattern = pattern.replace("####",buffer.toString());

		return str.replaceAll(pattern,"");
	}
	public static String encodingURL(String encodingString) throws UnsupportedEncodingException{
		return URLEncoder.encode(encodingString, "UTF-8");
	}
	/**
	 * ������¥�� �����ɴϴ�.
	 *
	 * @param beforeDay
	 * @return
	 */
	public static String getBeforeDay(int beforeDay){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH , beforeDay);

		return new java.text.SimpleDateFormat("yyyyMMdd").format(cal.getTime());
	}


	public static String getDCT(String str){
		String result = "";
		if(str.length() < 2){
			result = "0" + str;
		}else{
			result = str;
		}
		return result;
	}

	/**
	 * Ư����¥�� Ư���� ��ŭ ��¥�� ���Ѵ�.
	 * @param date Ư����¥ (2013-12-31)
	 * @param add Ư���Ⱓ(��) (1, 2, 3, 4...)
	 * @return
	 */
	public static String getAddMonth(String date, Object add, String datetxt) {
		int add_int = nvl(add,0);
		int year  = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(5, 7));
		int day = Integer.parseInt(date.substring(8, 10));

		month=month-1;   //����ϱ����� ���� -1 ����ߵȴ�.
		GregorianCalendar cld = new GregorianCalendar(year, month , day);

		if(datetxt.equals("Year")){
			cld.add(Calendar.YEAR, +add_int);     //�⿡ ����			
		}else if(datetxt.equals("Month")){
			cld.add(Calendar.MONTH, +add_int); 	  //���� ����			
		}else if(datetxt.equals("Day")){
			cld.add(Calendar.DATE, +add_int);     //�Ͽ� ����	
		}



		String cal_dt= CommonUtil.setDateFormat(cld, "yyyy-MM-dd");
		return cal_dt;
	}

	/**
	 * ��ȿȯ ��¥ �������� ��ȯ�Ѵ�.
	 * @param gc ���õ� ��
	 * @param fm ���Ϲ��
	 * @return
	 */
	public static String setDateFormat (GregorianCalendar gc , String fm) {
		if(fm == null) return fm;
		SimpleDateFormat sdf = new SimpleDateFormat(fm);
		return sdf.format(gc.getTime());
	}

	/**
	 * �������� ���ڰ�� �Լ�
	 * @param daechul_money	 �������
	 * @param v_rate		 ����ݸ�
	 * @param D_cnt			 ����Ⱓ(d)
	 * @return
	 */
	public static String getLijaIntresult(String daechul_money, String v_rate, String D_cnt){
		int tmp_daechul_money= nvl(Integer.parseInt(daechul_money),0);
		double tmp_v_rate= Double.parseDouble(v_rate);
		int tmp_D_cnt= nvl(Integer.parseInt(D_cnt),0);


		double li_ja = (int)((tmp_daechul_money * (tmp_v_rate/100)) * tmp_D_cnt/365 );
		return nvl(li_ja);
	}

	/**
	 * �ݾ��� �� ���ϴ� �Լ�.
	 * @param daechul_money
	 * @param month_money
	 * @return
	 */
	public static String getDaechulMoney(String daechul_money, String month_money){
		double temp_daechul_money = Double.parseDouble(daechul_money);
		double temp_month_money   = Double.parseDouble(month_money);
		int reseultmoney =  (int)temp_daechul_money - (int)temp_month_money;
		return nvl(reseultmoney);
	}

	/**
	 * �ݾ��� �� ���ϴ� �Լ�.
	 * @param daechul_money
	 * @param month_money
	 * @return
	 */
	public static String getDaechulMoneychou(String daechul_money, String month_money){
		double temp_daechul_money = Double.parseDouble(daechul_money);
		double temp_month_money   = Double.parseDouble(month_money);
		double reseultmoney =  temp_daechul_money * temp_month_money;
		return nvl(Math.round(reseultmoney));
	}	

	/**
	 * �ݾ��� �� ���ϴ� �Լ�.
	 * @param daechul_money
	 * @param month_money
	 * @return
	 */
	public static String getDaechulMoney2(String daechul_money, String month_money){
		int temp_daechul_money = nvl(Integer.parseInt(daechul_money),0);
		double temp_month_money   = Double.parseDouble(month_money);
		int reseultmoney =  temp_daechul_money + (int)temp_month_money;

		return nvl(reseultmoney);
	}		

	/**
	 * �ݾ��� ��/�� ���ϴ� �Լ�.
	 * @param daechul_money
	 * @param month_money
	 * @return
	 */
	public static String getDaechulMoney3(String daechul_money, String result, String li_ja){
		int temp_daechul_money = nvl(Integer.parseInt(daechul_money),0);
		double temp_month_money   = Double.parseDouble(result);
		double temp_li_ja   = Double.parseDouble(li_ja);
		int reseultmoney3 =  temp_daechul_money - (int)temp_month_money + (int)(temp_li_ja);
		return nvl(reseultmoney3);
	}		

	/**
	 * �ݾ��� ��/�� ���ϴ� �Լ�2.
	 * @param daechul_money
	 * @param month_money
	 * @return
	 */
	public static String getDaechulMoney4(String daechul_money, String result, String li_ja){
		double temp_daechul_money = Double.parseDouble(daechul_money);
		double temp_month_money   = Double.parseDouble(result);
		double temp_li_ja   = Double.parseDouble(li_ja);

		int reseultmoney3 =  (int)temp_daechul_money + ((int)temp_month_money - (int)(temp_li_ja));

		return nvl(reseultmoney3);
	}	

	/**
	 * �ݾ��� ��/�� ���ϴ� �Լ�2.
	 * @param daechul_money
	 * @param month_money
	 * @return
	 */
	public static String getDaechulMoney5(String daechul_money, String result, String li_ja){
		double temp_daechul_money = Double.parseDouble(daechul_money);
		double temp_month_money   = Double.parseDouble(result);
		double temp_li_ja   = Double.parseDouble(li_ja);

		int reseultmoney3 =  (int)temp_daechul_money + ((int)temp_month_money * (int)(temp_li_ja));

		return nvl(reseultmoney3);
	}	
	

	private static String dayDifference(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * Object �� Json �������� ��ȯ CommonCodeController ����
	 * @param model
	 * @param object
	 * @return
	 */
	public static String createObjectToJson(Model model,Object object){
		model.addAttribute("jsonData", object);
		return "common/jsonView";
	}

	/**
	 * ��� �ƴҶ�
	 * @return
	 */
	public static boolean isNotReal()
	{
		return System.getProperty("os.name").toLowerCase().indexOf("windows 7") > -1 ?  true : false; 
	}
	
	/**
	 * �ּҸ� ��ǥ�� ��ȯ�Ѵ� - ���̹� ���� api
	 * @param key
	 * @param query
	 * @return
	 */
	public static String[] getPositionForNaverMap(String key,String query){
		String[] returnStr = {"",""};
		try
		{
			// DOM Document ��ü�� �����ϴ� �ܰ�
			DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
			DocumentBuilder parser = f.newDocumentBuilder();
			query =  URLEncoder.encode(nvlTrim(query), "UTF-8");
			Document xmlDoc = null;
			String url ="http://openapi.map.naver.com/api/geocode.php?key="+nvlTrim(key)+"&encoding=UTF-8&coord=latlng&query="+nvlTrim(query);

			xmlDoc = parser.parse(url);  
			// ��Ʈ ������Ʈ ����
			Element root = xmlDoc.getDocumentElement();
			
			NodeList node = root.getElementsByTagName("item");
			//String userQuery = root.getElementsByTagName("userquery").item(0).getTextContent();
			
			if(node != null && node.getLength() > 0)
			{
				Node vNode = node.item(0);
				returnStr[1] = nvl(((Element) vNode).getElementsByTagName("x").item(0).getTextContent());
				returnStr[0] = nvl(((Element) vNode).getElementsByTagName("y").item(0).getTextContent());
			}
			
			return returnStr;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return returnStr;
		}
	}
	
	
	/**
	 * update �� ������ null �� "" �� �����Ŵ
	 * @param splitList
	 * @param params
	 * @return
	 */
	public static Map paramPutDefault(String splitList,Map params)
	{
		String[] splitListKey = split(splitList, "|");
		if(splitListKey != null && splitListKey.length > 0){
			for(String key : splitListKey)
			{
				params.put(key, CommonUtil.nvlTrim((params.get(key))));
			}
		}
		return params;
	}
	
	/**
	 * �������������� textarea���� <br />�� �ٹٲ����� ����
	 * @param resultData
	 * @return
	 */
	public static Map changeView(Map resultData){
		resultData.put("contents", CommonUtil.nvl(resultData.get("contents")).replace("<br />", "\n"));
		resultData.put("answer", CommonUtil.nvl(resultData.get("answer")).replace("<br />", "\n"));
		return resultData;
	}
	
	/**
	 * ���ϸ��� ��ȯ�մϴ�.
	 * @param file
	 * @return
	 */
	public static String cutFileName(String file){
		if(isNotEmpty(file) && !"".equals(nvl(file)) && file.indexOf("/") > -1)
			return file.substring(file.lastIndexOf("/")+1);
		else
			return file;
	}
	
	public static String filePathClear(String str,String str2){
		str += str2;
		return str.replace("\\", "/").replace("//", "/").replace(":/", "://");
	}
	
	public static String getStatus(String str){
		if("0".equals(str))
			return "ó����";
		else if("1".equals(str))
			return "ó�����";				
		else if("2".equals(str))
			return "ó���Ϸ�";				
		else if("3".equals(str))
			return "����";
		else
			return "";
	}
	
	/**
	 * �ش� parameter�� key���� ����
	 * @param params
	 * @param str
	 * @return
	 */
	public static Map parameterKeyReplace(Map params,String str){
		HashMap data = new HashMap<String, Comparable>();
		Iterator iter = params.keySet().iterator();
		while(iter.hasNext())
		{
			String key = CommonUtil.nvl(iter.next());
			Object values = (Object)params.get(key);
			
			if(key.indexOf(str) > -1) key = key.replace(str, "");
			
			data.put(key, values);
		}
		return data;
	}
	/**
	 * ������ ���ڿ��� ���ϴ� ���̸�ŭ ��ȯ�մϴ�.
	 *
	 * @param length ���ڿ� ����
	 * @return �������ڿ�
	 */
	public static String getRandomString(int length)
	{
		StringBuffer buffer = new StringBuffer();
		  Random random = new Random();
		 
		  String chars[] =
		    "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z".split(",");
		 
		  for (int i=0 ; i<length ; i++)
		  {
		    buffer.append(chars[random.nextInt(chars.length)]);
		  }
		  return buffer.toString();
	}
}