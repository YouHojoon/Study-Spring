package study.spring.dto;

import java.util.HashMap;
import java.util.Map;

public class BoardPageDTO {
	private int page;
	private Map<String, String> serch;
	public BoardPageDTO(int page) {
		this.page=(page-1)*10;
		serch=null;
	}
	public BoardPageDTO(int page,String type,String keyword) {
		this.page=(page-1)*10;
		serch=new HashMap<>();
		serch.put(type, keyword);
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public Map<String, String> getSerch() {
		return serch;
	}
	public void setSerch(Map<String, String> serch) {
		this.serch = serch;
	}
	
}
