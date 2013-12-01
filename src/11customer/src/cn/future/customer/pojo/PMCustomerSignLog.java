package cn.future.customer.pojo;

import java.io.Serializable;
import java.util.Date;

public class PMCustomerSignLog implements Serializable{

	/**
	 * 使用Mongo存储
	 */
	private static final long serialVersionUID = 1455209556626121368L;
	private String id;
	private String customerId;
	private String userIp;
	private String userBrowserType;
	private String userBrowserVersion;
	private int isSignIn;  //0 out 1  in
	private long dateLong;
	private Date date;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getUserIp() {
		return userIp;
	}
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	public String getUserBrowserType() {
		return userBrowserType;
	}
	public void setUserBrowserType(String userBrowserType) {
		this.userBrowserType = userBrowserType;
	}
	public String getUserBrowserVersion() {
		return userBrowserVersion;
	}
	public void setUserBrowserVersion(String userBrowserVersion) {
		this.userBrowserVersion = userBrowserVersion;
	}
	public int getIsSignIn() {
		return isSignIn;
	}
	public void setIsSignIn(int isSignIn) {
		this.isSignIn = isSignIn;
	}
	public long getDateLong() {
		return dateLong;
	}
	public void setDateLong(long dateLong) {
		this.dateLong = dateLong;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
