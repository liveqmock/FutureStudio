package cn.future.common.pojo;

import java.io.Serializable;

public class DStatus implements Serializable{
	private static final long serialVersionUID = -2403770323772258691L;
	private int statusCode;
	private String statusName;
	
	public DStatus(int code,String name){
		this.statusCode=code;
		this.statusName=name;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
