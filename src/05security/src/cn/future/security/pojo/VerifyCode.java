package cn.future.security.pojo;

import java.io.Serializable;

public class VerifyCode implements Serializable{
	private static final long serialVersionUID = 6807083151756129625L;
	private String imgUrl;
	private String vcodeKey;
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getVcodeKey() {
		return vcodeKey;
	}
	public void setVcodeKey(String vcodeKey) {
		this.vcodeKey = vcodeKey;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
