package study.spring.vo;

import java.net.URLEncoder;

public class BoardAttachFileVO {
	private String fileName;
	private String uploadPath;
	private String uuid;
	private boolean image;
	private String encodePath;
	
	public String getEncodePath() {
		return encodePath;
	}
	public void setEncodePath(String uploadPath,String fileName) {
		try {
			encodePath=URLEncoder.encode(uploadPath+"\\"+fileName,"UTF-8");
		}catch(Exception e) {e.printStackTrace();}
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getUploadPath() {
		return uploadPath;
	}
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public boolean isImage() {
		return image;
	}
	public void setImage(boolean image) {
		this.image = image;
	}
	
}
