package cn.future.finance.dto;

import java.io.Serializable;

import cn.future.finance.pojo.PFinanceProjectBidDetail;
import cn.future.finance.util.FinanceBidStatusUtil;
import cn.future.finance.util.FinanceMoneyUtil;
import cn.future.util.TimeUtil;

public class DFinanceProjectBidDetail implements Serializable{
	
	public DFinanceProjectBidDetail(PFinanceProjectBidDetail p, boolean security){
		this.id = p.getId();
		this.financeProjectId = p.getFinanceProjectId();
		this.customerId = p.getCustomerId();
		this.customerName = p.getCustomerName();
		this.bidDate = TimeUtil.dateToYMDHMSString(p.getBidDate());
		this.cashNumber = FinanceMoneyUtil.formatThreeSplit(p.getCashNumber()/100);
		this.canceledBid = p.getCanceledBid();
		this.statusCode = p.getStatusCode();
		this.statusName = FinanceBidStatusUtil.getStatusName(this.statusCode);
		this.customerNickName = p.getCustomerNickName();
		if(security){
			this.customerId = this.customerId.substring(0, 1)+"**";
			this.customerName = "*";
			this.statusName = "";
		}
	}

	private static final long serialVersionUID = -4566097196125042635L;
	private String id;
	private String financeProjectId;
	private String financeProjectFlowId;
	private String customerId;
	private String customerName;
	private String customerNickName;
	
	private String bidDate;
	private String cashNumber;

	private int canceledBid;// 默认1，如果是0，说明是用户主动撤销投标
	private int statusCode;  //标记位  100自动投标  1用户手动投标
	private String statusName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getBidDate() {
		return bidDate;
	}
	public void setBidDate(String bidDate) {
		this.bidDate = bidDate;
	}

	
	public String getCashNumber() {
		return cashNumber;
	}
	public void setCashNumber(String cashNumber) {
		this.cashNumber = cashNumber;
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
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getFinanceProjectFlowId() {
		return financeProjectFlowId;
	}
	public void setFinanceProjectFlowId(String financeProjectFlowId) {
		this.financeProjectFlowId = financeProjectFlowId;
	}
	public String getCustomerNickName() {
		return customerNickName;
	}
	public void setCustomerNickName(String customerNickName) {
		this.customerNickName = customerNickName;
	}
	
	
}
