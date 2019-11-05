package study.spring.dto;

public class RpageDTO {
	private int num;
	private int rpage;
	
	public RpageDTO(int num, int rpage){
		this.num=num;
		this.rpage=(rpage-1)*10;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getRpage() {
		return rpage;
	}
	
	
}
