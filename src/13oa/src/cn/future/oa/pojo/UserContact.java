package cn.future.oa.pojo;

import java.io.Serializable;

public class UserContact implements Serializable{
	/**
	 * 这个表暂时废弃
	 */
	private static final long serialVersionUID = 8614859511497822174L;
	//contact info
	/**
	 * 主键id, 同时也是宿主ID
	 */
	private String id;
	/**
	 * 职位信息
	 */
	private String position;
	/**
	 * 英文名
	 */
	private String nameEnglish;
	/**
	 * 工作电话
	 */
	private String mobileWork;
	/**
	 * 工作邮箱
	 */
	private String emailWork;
	/**
	 * 工作地址
	 */
	private String addressWork;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getNameEnglish() {
		return nameEnglish;
	}

	public void setNameEnglish(String nameEnglish) {
		this.nameEnglish = nameEnglish;
	}

	public String getMobileWork() {
		return mobileWork;
	}

	public void setMobileWork(String mobileWork) {
		this.mobileWork = mobileWork;
	}

	public String getEmailWork() {
		return emailWork;
	}

	public void setEmailWork(String emailWork) {
		this.emailWork = emailWork;
	}

	public String getAddressWork() {
		return addressWork;
	}

	public void setAddressWork(String addressWork) {
		this.addressWork = addressWork;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
