package cn.future.pay.pojo;

import java.io.Serializable;
import java.util.Date;

public class TenpayReturn implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3965894158215554875L;
	private String id ; //同申请ID  -- out_trade_no
	private String notifyId; //通知ID
	private String transactionId; //财付通订单号
	private double payTotalFee;
	private double discount;
	private int tradeStateCode; // 0 表示成功，其他表示失败  都采用负数
	private String tradeStateName; //util转
	private String tradeModeCode; //1及时到账  2 担保交易
	private Date receiveTime;

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
	public double getPayTotalFee() {
		return payTotalFee;
	}
	public void setPayTotalFee(double payTotalFee) {
		this.payTotalFee = payTotalFee;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public int getTradeStateCode() {
		return tradeStateCode;
	}
	public void setTradeStateCode(int tradeStateCode) {
		this.tradeStateCode = tradeStateCode;
	}
	public String getTradeStateName() {
		return tradeStateName;
	}
	public void setTradeStateName(String tradeStateName) {
		this.tradeStateName = tradeStateName;
	}
	public String getTradeModeCode() {
		return tradeModeCode;
	}
	public void setTradeModeCode(String tradeModeCode) {
		this.tradeModeCode = tradeModeCode;
	}
	public Date getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}
	
	
}
