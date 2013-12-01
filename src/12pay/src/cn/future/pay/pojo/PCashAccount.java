package cn.future.pay.pojo;

import java.io.Serializable;
import java.util.Date;

public class PCashAccount implements Serializable{
	private static final long serialVersionUID = -2922273639231166437L;
	public static final int ACCOUNTTYPE_USER = 1;
	public static final int ACCOUNTTYPE_CUSTOMER = 2;
	//需要事务  -- 只统计当前现金数
	private String id;  //同绑定ID
	private int version;
	private int accountTypeCode;// 1 user, 2 customer;
	private int bindPayAccountType; // 1,2   ---  0未绑定
	private String bindPayAccount; 
	private long cashNumber; //分s
	private Date lastUpdate;
	public PCashAccount(){
		
	};
	/**
	 * 创建一个新账户
	 * @param relatedId
	 * @param accountType
	 */
	public PCashAccount(
			String relatedId, int accountType){
		this.id = relatedId;
		this.version = 1;
		this.accountTypeCode = accountType;
		this.bindPayAccountType = 0;
		this.bindPayAccount = "";
		this.cashNumber = 0;
		this.lastUpdate = new Date();
	}	
	
	public int getAccountTypeCode() {
		return accountTypeCode;
	}
	public void setAccountTypeCode(int accountTypeCode) {
		this.accountTypeCode = accountTypeCode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public int getBindPayAccountType() {
		return bindPayAccountType;
	}
	public void setBindPayAccountType(int bindPayAccountType) {
		this.bindPayAccountType = bindPayAccountType;
	}
	public String getBindPayAccount() {
		return bindPayAccount;
	}
	public void setBindPayAccount(String bindPayAccount) {
		this.bindPayAccount = bindPayAccount;
	}

	public long getCashNumber() {
		return cashNumber;
	}
	public void setCashNumber(long cashNumber) {
		this.cashNumber = cashNumber;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
