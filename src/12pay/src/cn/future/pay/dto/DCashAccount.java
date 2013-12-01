package cn.future.pay.dto;

import cn.future.pay.pojo.PCashAccount;
import cn.future.pay.util.PayCodeUtil;
import cn.future.util.TimeUtil;

public class DCashAccount {
	
	public DCashAccount(){
		
	}
	public DCashAccount(PCashAccount p){
		this.id = p.getId();
		switch(p.getAccountTypeCode()){
		case PCashAccount.ACCOUNTTYPE_USER:
			this.accountTypeName = "用户";
			break;
		case PCashAccount.ACCOUNTTYPE_CUSTOMER:
			this.accountTypeName = "客户";
			break;
		}
		this.bindPayAccountTypeName = PayCodeUtil.getPlatformName(p.getBindPayAccountType());
		this.bindPayAccount = p.getBindPayAccount();
		this.cashNumber = p.getCashNumber()/100 + "";
		this.lastUpdate = TimeUtil.date2ShowYMDHMS(p.getLastUpdate());
	}
	private String id;  //同绑定ID
	private String accountTypeName;// 1 user, 2 customer;
	private String bindPayAccountTypeName; // 1,2   ---  0未绑定
	private String bindPayAccount; 
	private String cashNumber; //分s
	private String lastUpdate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccountTypeName() {
		return accountTypeName;
	}
	public void setAccountTypeName(String accountTypeName) {
		this.accountTypeName = accountTypeName;
	}
	public String getBindPayAccountTypeName() {
		return bindPayAccountTypeName;
	}
	public void setBindPayAccountTypeName(String bindPayAccountTypeName) {
		this.bindPayAccountTypeName = bindPayAccountTypeName;
	}
	public String getBindPayAccount() {
		return bindPayAccount;
	}
	public void setBindPayAccount(String bindPayAccount) {
		this.bindPayAccount = bindPayAccount;
	}

	public String getCashNumber() {
		return cashNumber;
	}
	public void setCashNumber(String cashNumber) {
		this.cashNumber = cashNumber;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	
}
