package cn.future.pay.dto;

import java.io.Serializable;

import cn.future.finance.util.FinanceMoneyUtil;
import cn.future.pay.pojo.PWithdrawRequest;
import cn.future.pay.util.WithdrawRequestCodeUtil;
import cn.future.util.TimeUtil;

public class DWithdrawRequest implements Serializable{

	private static final long serialVersionUID = -6864730493650558769L;
	
	public DWithdrawRequest(){
		
	}
	public DWithdrawRequest(PWithdrawRequest p){
		this.id = p.getId();
		this.number = FinanceMoneyUtil.formatThreeSplitFen(p.getNumber());
		this.addDate = TimeUtil.date2ShowYMDHMS(p.getAddDate());
		this.securityDate = TimeUtil.date2ShowYMDHMS(p.getSecurityDate());
		
		this.payDate = TimeUtil.date2ShowYMDHMS(p.getPayDate());
		this.payOrderId = p.getPayOrderId();
		this.payResult = p.getPayResult();
		
		this.targetAccount = p.getTargetAccount();
		this.targetAccountCode = p.getTargetAccountCode();
		this.targetAccountName = p.getTargetAccountName();
		this.statusName = WithdrawRequestCodeUtil.getRequestCodeName(p.getStatusCode());
	}
	private String id; //同为取款的business ID
	private String number;
	
	private String addDate;
	private String securityDate;
	
	private String payDate;
	private String payOrderId;
	private String payResult;

	private String targetAccount;
	private int targetAccountCode;
	private String targetAccountName;
	
	private int statusCode; // -10 提交了申请   0通过了安全验证   10 已经付款
	private String statusName;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getAddDate() {
		return addDate;
	}
	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}
	public String getSecurityDate() {
		return securityDate;
	}
	public void setSecurityDate(String securityDate) {
		this.securityDate = securityDate;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
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
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
