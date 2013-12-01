package cn.future.customer.pojo;

import java.io.Serializable;
import java.util.Date;

import cn.future.util.StringUtil;

public class PCustomerPreRegister implements Serializable{

	private static final long serialVersionUID = 8303473266037467422L;

	private String id;
	private String activeId; //用于激活这个账户的ID 使用了就丢弃
	private String accountName;//email 不必唯一
	private String password;
	private String cnName;
	private String mobilePhone;
	private Date addDate;
	private String adviserEmployId;
	/**
	 * 构造函数用于创建新账户注册信息
	 * @param accountName
	 * @param password
	 * @param cnName
	 * @param mobilePhone
	 * @param adviserEmployId
	 */
	public PCustomerPreRegister(String accountName, String password, String cnName, String mobilePhone, String adviserEmployId){
		this.id = StringUtil.getUuid();
		this.addDate = new Date();
		
		this.accountName = accountName;
		this.password = password;
		this.cnName = cnName;
		this.mobilePhone = mobilePhone;
		this.adviserEmployId = adviserEmployId;
	}
	public PCustomerPreRegister(){
		
	}

	public String getAdviserEmployId() {
		return adviserEmployId;
	}
	public void setAdviserEmployId(String adviserEmployId) {
		this.adviserEmployId = adviserEmployId;
	}
	public String getCnName() {
		return cnName;
	}
	public void setCnName(String cnName) {
		this.cnName = cnName;
	}
	public String getActiveId() {
		return activeId;
	}
	public void setActiveId(String activeId) {
		this.activeId = activeId;
	}
	public Date getAddDate() {
		return addDate;
	}
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
