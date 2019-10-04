package study.spring.vo;

import java.sql.Timestamp;

public class BoardVO {
	private int num;
	private String title;
	private String content;
	private String writer;
	private Timestamp reg_date;
	private Timestamp update_date;
	
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
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Timestamp getUpdate_date() {
		return update_date;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	public int getNum() {
		return num;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	@Override
	public String toString() {
		return "BoardVO [num=" + num + ", title=" + title + ", content=" + content + ", writer=" + writer
				+ ", reg_date=" + reg_date + ", update_date=" + update_date + "]";
	}
}
