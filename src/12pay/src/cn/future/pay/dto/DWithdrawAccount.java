package cn.future.pay.dto;

import java.io.Serializable;

import cn.future.pay.pojo.PWithdrawAccount;
import cn.future.util.TimeUtil;

/**
 * 取现账户
 */
public class DWithdrawAccount implements Serializable {
	private static final long serialVersionUID = 7911042432191811734L;
	
	private String id;
	private String userName;
	private String bindAccount;
	private int bindTypeCode;
	private String bindTypeName;
	private String bindTypeLocation;
	private String lastUpdate;
	private int statusCode;
	public DWithdrawAccount(){
		
	}
	public DWithdrawAccount(PWithdrawAccount p){
		this.id = p.getId();
		this.userName = p.getUserName();
		this.bindAccount = p.getBindAccount();
		this.bindTypeCode = p.getBindTypeCode();
		this.bindTypeName = p.getBindTypeName();
		this.bindTypeLocation = p.getBindTypeLocation();
		this.lastUpdate = TimeUtil.date2ShowYMDHMS(p.getLastUpdate());
		this.statusCode = p.getStatusCode();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getBindTypeLocation() {
		return bindTypeLocation;
	}
	public void setBindTypeLocation(String bindTypeLocation) {
		this.bindTypeLocation = bindTypeLocation;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
