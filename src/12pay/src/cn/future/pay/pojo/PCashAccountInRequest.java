package cn.future.pay.pojo;

import java.io.Serializable;
import java.util.Date;

public class PCashAccountInRequest  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5733625474315450416L;
	//需要事务
	private String id;
	private int version;
	private int inNumber; //单位是分
	private Date date;
	private Date overDate;
	private int paySystemCode;
	private int statusCode;
	
	//下面是 各支付平台返回的信息
	private String paySystemId;
	private String paySystemReturn; //直接回调返回信息
	private String paySystemNotify; //通过返回信息进行查询的结果
	
	
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

	
	public int getInNumber() {
		return inNumber;
	}
	public void setInNumber(int inNumber) {
		this.inNumber = inNumber;
	}
	public String getPaySystemId() {
		return paySystemId;
	}
	public void setPaySystemId(String paySystemId) {
		this.paySystemId = paySystemId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getOverDate() {
		return overDate;
	}
	public void setOverDate(Date overDate) {
		this.overDate = overDate;
	}
	public int getPaySystemCode() {
		return paySystemCode;
	}
	public void setPaySystemCode(int paySystemCode) {
		this.paySystemCode = paySystemCode;
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getPaySystemReturn() {
		return paySystemReturn;
	}
	public void setPaySystemReturn(String paySystemReturn) {
		this.paySystemReturn = paySystemReturn;
	}
	public String getPaySystemNotify() {
		return paySystemNotify;
	}
	public void setPaySystemNotify(String paySystemNotify) {
		this.paySystemNotify = paySystemNotify;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
