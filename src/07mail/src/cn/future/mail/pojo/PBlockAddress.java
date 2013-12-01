package cn.future.mail.pojo;

import java.io.Serializable;
import java.util.Date;

public class PBlockAddress implements Serializable{
	private static final long serialVersionUID = 674469843995562571L;
	private String id;
	private String mailAddress;
	private Date addDate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public Date getAddDate() {
		return addDate;
	}
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
