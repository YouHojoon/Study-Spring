package study.spring.vo;

import java.sql.Timestamp;


public class ReplyVO {
	private int rnum;
	private int num;
	private String reply;
	private String replyer;
	private Timestamp reg_date;
	private Timestamp update_date;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public String getReplyer() {
		return replyer;
	}
	public void setReplyer(String replyer) {
		this.replyer = replyer;
	}
	public Timestamp getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Timestamp update_date) {
		this.update_date = update_date;
	}
	public int getRnum() {
		return rnum;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	
	
}
