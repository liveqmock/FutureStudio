package cn.future.customer.dto;

import java.io.Serializable;

import cn.future.customer.pojo.PCustomerPreRegister;
import cn.future.util.TimeUtil;

public class DCustomerPreRegister implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7193502188121247455L;
	private String id;
	private String activeId; //用于激活这个账户的ID 使用了就丢弃
	private String accountName;//email 不必唯一
	private String cnName;
	private String mobilePhone;
	private String addDate;
	
	public DCustomerPreRegister(PCustomerPreRegister p ){
		this.id = p.getId();
		this.accountName = p.getAccountName();
		this.activeId = p.getActiveId();
		this.cnName = p.getCnName();
		this.mobilePhone = p.getMobilePhone();
		this.addDate = TimeUtil.date2ShowYMDHMS(p.getAddDate());
	}
	public DCustomerPreRegister(){
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getActiveId() {
		return activeId;
	}
	public void setActiveId(String activeId) {
		this.activeId = activeId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getCnName() {
		return cnName;
	}
	public void setCnName(String cnName) {
		this.cnName = cnName;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getAddDate() {
		return addDate;
	}
	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
