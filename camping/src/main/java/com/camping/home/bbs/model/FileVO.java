package com.camping.home.bbs.model;

public class FileVO {
	
	private int idx;
	private int board_idx;
	private String file_name;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getBoard_idx() {
		return board_idx;
	}
	public void setBoard_idx(int board_idx) {
		this.board_idx = board_idx;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	
	@Override
	public String toString() {
		return "FileVO [idx=" + idx + ", board_idx=" + board_idx + ", file_name=" + file_name + "]";
	}
	
	
	
	
}
