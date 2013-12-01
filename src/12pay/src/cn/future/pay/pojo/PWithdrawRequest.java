package cn.future.pay.pojo;

import java.io.Serializable;
import java.util.Date;

import cn.future.util.StringUtil;

public class PWithdrawRequest implements Serializable{

	private static final long serialVersionUID = -6864730493650558769L;
	
	public PWithdrawRequest(){
		
	}
	public PWithdrawRequest(String userId, long number, String requestIp, int targetAccountCode,
				String targetAccountName, String targetAccount 
			){
		this.id = StringUtil.getUuid();
		this.version = 1;
		this.userId = userId;
		this.number = number;
		this.requestIp = requestIp;
		
		this.addDate = new Date();
		
		this.targetAccount = targetAccount;
		this.targetAccountCode = targetAccountCode;
		this.targetAccountName = targetAccountName;
		
		this.statusCode = -10;
	}
	private String id; //同为取款的business ID
	private int version;
	private String userId;
	private long number;
	private String requestIp;
	
	private Date addDate;
	private Date securityDate;
	
	private Date payDate;
	private String payOrderId;
	private String payResult;

	private String targetAccount;
	private int targetAccountCode;
	private String targetAccountName;
	
	private int statusCode; // -10 提交了申请   0等待系统处理   10 已经付款

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
	public long getNumber() {
		return number;
	}
	public void setNumber(long number) {
		this.number = number;
	}
	public String getRequestIp() {
		return requestIp;
	}
	public void setRequestIp(String requestIp) {
		this.requestIp = requestIp;
	}
	public Date getAddDate() {
		return addDate;
	}
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	public Date getSecurityDate() {
		return securityDate;
	}
	public void setSecurityDate(Date securityDate) {
		this.securityDate = securityDate;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public String getPayOrderId() {
		return payOrderId;
	}
	public void setPayOrderId(String payOrderId) {
		this.payOrderId = payOrderId;
	}
	public String getPayResult() {
		return payResult;
	}
	public void setPayResult(String payResult) {
		this.payResult = payResult;
	}
	public String getTargetAccount() {
		return targetAccount;
	}
	public void setTargetAccount(String targetAccount) {
		this.targetAccount = targetAccount;
	}
	public int getTargetAccountCode() {
		return targetAccountCode;
	}
	public void setTargetAccountCode(int targetAccountCode) {
		this.targetAccountCode = targetAccountCode;
	}
	public String getTargetAccountName() {
		return targetAccountName;
	}
	public void setTargetAccountName(String targetAccountName) {
		this.targetAccountName = targetAccountName;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
