package cn.future.pay.pojo;

import java.io.Serializable;
import java.util.Date;

import cn.future.util.StringUtil;

/**
 * 取现账户
 */
public class PWithdrawAccount implements Serializable {
	private static final long serialVersionUID = 7911042432191811734L;
	
	private String id;
	private String userId;
	private String bindAccount;
	private String userName;
	private int bindTypeCode;
	private String bindTypeName;
	private String bindTypeLocation;
	private Date lastUpdate;
	private int statusCode;
	
	public PWithdrawAccount(){
		
	}
	public PWithdrawAccount(String userId, String bindAccount, String userName,
			int bindTypeCode, String bindTypeName, String bindTypeLocation){
		this.id = StringUtil.getUuid();
		this.userId = userId;
		this.bindAccount = bindAccount;
		this.userName = userName;
		this.bindTypeCode = bindTypeCode;
		this.bindTypeName = bindTypeName;
		this.bindTypeLocation = bindTypeLocation;
		this.lastUpdate = new Date();
		this.statusCode = 1;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBindAccount() {
		return bindAccount;
	}
	public void setBindAccount(String bindAccount) {
		this.bindAccount = bindAccount;
	}
	public int getBindTypeCode() {
		return bindTypeCode;
	}
	public void setBindTypeCode(int bindTypeCode) {
		this.bindTypeCode = bindTypeCode;
	}
	public String getBindTypeName() {
		return bindTypeName;
	}
	public void setBindTypeName(String bindTypeName) {
		this.bindTypeName = bindTypeName;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getBindTypeLocation() {
		return bindTypeLocation;
	}
	public void setBindTypeLocation(String bindTypeLocation) {
		this.bindTypeLocation = bindTypeLocation;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	
}
