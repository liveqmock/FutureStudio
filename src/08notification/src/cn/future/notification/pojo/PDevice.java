package cn.future.notification.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户管理的设备
 * @author Tony
 */
public class PDevice implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1417250635268029971L;
	private String id;
	private String deviceName;
	private String accountId;
	private String accountName;
	private String deviceInfoA;
	private String deviceInfoB;
	private String deviceInfoC;
	private String deviceInfo;//存放手机信息
	private String deviceType; //android ios
	private String deviceTypeName;
	private Date addDate;
	
	public String getDeviceInfo() {
		return deviceInfo;
	}
	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getDeviceTypeName() {
		return deviceTypeName;
	}
	public void setDeviceTypeName(String deviceTypeName) {
		this.deviceTypeName = deviceTypeName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getDeviceInfoA() {
		return deviceInfoA;
	}
	public void setDeviceInfoA(String deviceInfoA) {
		this.deviceInfoA = deviceInfoA;
	}
	public String getDeviceInfoB() {
		return deviceInfoB;
	}
	public void setDeviceInfoB(String deviceInfoB) {
		this.deviceInfoB = deviceInfoB;
	}
	public String getDeviceInfoC() {
		return deviceInfoC;
	}
	public void setDeviceInfoC(String deviceInfoC) {
		this.deviceInfoC = deviceInfoC;
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
