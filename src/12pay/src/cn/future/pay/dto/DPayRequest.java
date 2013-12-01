package cn.future.pay.dto;

import cn.future.customer.dto.DCustomer;
import cn.future.finance.util.FinanceMoneyUtil;
import cn.future.pay.pojo.PPayRequest;
import cn.future.pay.util.BankCodeUtil;
import cn.future.pay.util.PayCodeUtil;
import cn.future.util.TimeUtil;

public class DPayRequest {
	public DPayRequest (){
		
	}
	public DPayRequest (PPayRequest p){
		this.id = p.getId();
		this.notifyId = p.getNotifyId();
		this.transactionId = p.getTransactionId();
		this.bankId = p.getBankId();
		this.bankName = BankCodeUtil.getBankName(p.getBankId());
		this.businessId = p.getBusinessId();
		this.customerId = p.getCashAccountId();
		this.totalNumber = FinanceMoneyUtil.formatThreeSplitFen(p.getTotalNumber());
		this.productNumber = FinanceMoneyUtil.formatThreeSplitFen(p.getProductNumber());
		this.transNumber = FinanceMoneyUtil.formatThreeSplitFen(p.getTransNumber());
		
		this.title = p.getTitle();
		this.content = p.getContent();
		this.transType = p.getTransType();
		this.transportDesc = p.getTransportDesc();
		this.statusName = PayCodeUtil.getPayStatusName(p.getStatusCode());

		this.createDate = TimeUtil.date2ShowYMDHMS(p.getCreateDate());
		this.deadDate = TimeUtil.date2ShowYMDHMS(p.getDeadDate());
		this.finishDate = TimeUtil.date2ShowYMDHMS(p.getFinishDate());
	}
	private String id;
	private String notifyId;//通知ID
	private String transactionId;//第三方支付订单ID
	private String bankId; //使用的银行编号
	private String bankName;
	
	private String businessId; //其他系统的业务ID,256位，较长
	private String customerId;
	private DCustomer customer;
	
	private String totalNumber;
	private String productNumber;
	private String transNumber;
	
	private String title;
	private String content;
	private String transType;
	private String transportDesc;
	private String statusName;
	
	private String createDate;
	private String deadDate;
	private String finishDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNotifyId() {
		return notifyId;
	}
	public void setNotifyId(String notifyId) {
		this.notifyId = notifyId;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public DCustomer getCustomer() {
		return customer;
	}
	public void setCustomer(DCustomer customer) {
		this.customer = customer;
	}
	public String getTotalNumber() {
		return totalNumber;
	}
	public void setTotalNumber(String totalNumber) {
		this.totalNumber = totalNumber;
	}
	public String getProductNumber() {
		return productNumber;
	}
	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}
	public String getTransNumber() {
		return transNumber;
	}
	public void setTransNumber(String transNumber) {
		this.transNumber = transNumber;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public String getTransportDesc() {
		return transportDesc;
	}
	public void setTransportDesc(String transportDesc) {
		this.transportDesc = transportDesc;
	}

	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getDeadDate() {
		return deadDate;
	}
	public void setDeadDate(String deadDate) {
		this.deadDate = deadDate;
	}
	public String getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	
	
}
