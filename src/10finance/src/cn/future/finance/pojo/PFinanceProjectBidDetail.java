package cn.future.finance.pojo;

import java.io.Serializable;
import java.util.Date;

import cn.future.util.StringUtil;

public class PFinanceProjectBidDetail implements Serializable{
	public static final int STATUSCODE_AUTOBID = 100; //自动投标
	public static final int STATUSCODE_CUSTOMERMANUAL = 1; //用户手动投标
	
	public PFinanceProjectBidDetail(String projectId,
			String customerId,
			String customerName,
			String customerNickName,
			String customerAccount,
			long cashNumber,
			int statusCode){
		this.id = StringUtil.getUuid();
		this.financeProjectId = projectId;
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerNickName = customerNickName;
		this.customerAccount = customerAccount;
		this.bidDate =  new Date();
		this.cashNumber = cashNumber;
		this.alreadyReturn = 0;
		this.canceledBid = 1;
		this.statusCode = statusCode;
	}
	public PFinanceProjectBidDetail(){
		
	}
	private static final long serialVersionUID = 2878290691713538278L;
	private String id;
	private int version;
	private String financeProjectId;
	private String customerId;
	private String customerNickName;
	private String customerName; //acount name;
	private String customerAccount;
	private Date bidDate;
	private long cashNumber;
	/**
	 * 退回金额，初始化0
	 */
	private long alreadyReturn = 0;
	/**
	 * 默认1，如果是0，说明是用户主动撤销投标 --暂时不生效字段
	 */
	private int canceledBid;
	/**
	 * 标记位  
	 * 200系统后台投标，显示为手动投标，但是是特殊类型
	 * 100自动投标  
	 * 10用户手动投标
	 * -1投标已完成结算
	 */
	private int statusCode;
	
	

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
	public String getFinanceProjectId() {
		return financeProjectId;
	}
	public void setFinanceProjectId(String financeProjectId) {
		this.financeProjectId = financeProjectId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public String getCustomerAccount() {
		return customerAccount;
	}
	public void setCustomerAccount(String customerAccount) {
		this.customerAccount = customerAccount;
	}
	public Date getBidDate() {
		return bidDate;
	}
	public void setBidDate(Date bidDate) {
		this.bidDate = bidDate;
	}

	public long getCashNumber() {
		return cashNumber;
	}
	public void setCashNumber(long cashNumber) {
		this.cashNumber = cashNumber;
	}
	public long getAlreadyReturn() {
		return alreadyReturn;
	}
	public void setAlreadyReturn(long alreadyReturn) {
		this.alreadyReturn = alreadyReturn;
	}
	public int getCanceledBid() {
		return canceledBid;
	}
	public void setCanceledBid(int canceledBid) {
		this.canceledBid = canceledBid;
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
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerNickName() {
		return customerNickName;
	}
	public void setCustomerNickName(String customerNickName) {
		this.customerNickName = customerNickName;
	}	
	
}
