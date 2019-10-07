package study.spring.dto;

public class PageDTO {
	
	private int startPage;
	private int endPage;
	private int total;
	private int page;
	private int realEnd;
	private boolean next;
	private boolean prev;
	
	public PageDTO(int page,int total) {
		this.page=page;
		this.total=total;
		realEnd=(int)Math.ceil(total/10.0);
		endPage=(int)Math.ceil(page/10.0)*10;
		startPage=endPage-9;
		if(realEnd<endPage)
			endPage=realEnd;
		next=endPage<realEnd;
		prev=startPage>1;
	}

	public boolean isNext() {
		return next;
	}

	public boolean isPrev() {
		return prev;
	}

	public int getStartPage() {
		return startPage;
	}
	public int getEndPage() {
		return endPage;
	}

	public int getRealEnd() {
		return realEnd;
	}
	public int getPage() {
		return page;
	}

}
