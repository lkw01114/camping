package com.camping.home.bbs.model;

import java.util.Date;

public class Bbs {
	private int idx;
	private int board_type;
	private String title;
	private String content;
	private String reg_id;
	private Date reg_date;
	private String modify_id;
	private Date modify_date;
	private int readnum;
	private int recommend;
	private String del_flag;
	private int ref;
	private int re_step;
	private int re_level;
	private String board_name;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getBoard_type() {
		return board_type;
	}
	public void setBoard_type(int board_type) {
		this.board_type = board_type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReg_id() {
		return reg_id;
	}
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public String getModify_id() {
		return modify_id;
	}
	public void setModify_id(String modify_id) {
		this.modify_id = modify_id;
	}
	public Date getModify_date() {
		return modify_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
	public int getReadnum() {
		return readnum;
	}
	public void setReadnum(int readnum) {
		this.readnum = readnum;
	}
	public int getRecommend() {
		return recommend;
	}
	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}
	public String getDel_flag() {
		return del_flag;
	}
	public void setDel_flag(String del_flag) {
		this.del_flag = del_flag;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getRe_step() {
		return re_step;
	}
	public void setRe_step(int re_step) {
		this.re_step = re_step;
	}
	public int getRe_level() {
		return re_level;
	}
	public void setRe_level(int re_level) {
		this.re_level = re_level;
	}
	public String getBoard_name() {
		return board_name;
	}
	public void setBoard_name(String board_name) {
		this.board_name = board_name;
	}
	@Override
	public String toString() {
		return "Bbs [idx=" + idx + ", board_type=" + board_type + ", title=" + title + ", content=" + content
				+ ", reg_id=" + reg_id + ", reg_date=" + reg_date + ", modify_id=" + modify_id + ", modify_date="
				+ modify_date + ", readnum=" + readnum + ", recommend=" + recommend + ", del_flag=" + del_flag
				+ ", ref=" + ref + ", re_step=" + re_step + ", re_level=" + re_level + ", board_name=" + board_name
				+ "]";
	}
	
	
	
	
	
}
