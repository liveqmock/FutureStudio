package cn.future.pay.pojo;

import java.util.Date;

import cn.future.common.service.impl.ConfigHelperImpl;
import cn.future.pay.util.BaoMD5Util;
import cn.future.pay.util.BaopayBankCodeUtil;
import cn.future.util.StringUtil;
import cn.future.util.TimeUtil;

public class BaoRequest {
	public BaoRequest(){
		
	}
	public BaoRequest(PPayRequest p){
		this.transID = p.getId();
		this.merchantID = ConfigHelperImpl.getProperty("baoId", "");
		this.payID = BaopayBankCodeUtil.getBaoBankId(p.getBankId());
		this.orderMoney = String.valueOf(p.getTotalNumber());
		this.productName = StringUtil.UrlEncoder(p.getTitle());
		
		this.merchant_url= ConfigHelperImpl.getProperty("webUrl", "")+
				ConfigHelperImpl.getProperty("payFinishiUrl", "");
		this.return_url = ConfigHelperImpl.getProperty("securityUrl", "")+
				"/fs/pay/baoCallBack_check.action";
		String md5Key = ConfigHelperImpl.getProperty("baoKey","");
		String sign = BaoMD5Util.getMD5ofStr(this, md5Key);
		this.md5Sign = sign;
	}
	/**
	 * 业务系统主键
	 */
	private String transID;
	private String merchantID;//商户号
	private String payID;
	private String tradeDate=TimeUtil.dateToString("yyyyMMddHHmmss", new Date());
	private String orderMoney;
	private String productName;//商品名称
	private String amount="1";//商品数量
	private String productLogo="";
	private String username="";
	private String email="";
	private String mobile="";
	private String additionalInfo="";
	private String merchant_url;
	private String return_url;
	private String noticeType="1"; //保证一定有返回信息
	private String md5Sign;
	
	public String getTransID() {
		return transID;
	}
	public void setTransID(String transID) {
		this.transID = transID;
	}
	public String getMerchantID() {
		return merchantID;
	}
	public void setMerchantID(String merchantID) {
		this.merchantID = merchantID;
	}
	public String getPayID() {
		return payID;
	}
	public void setPayID(String payID) {
		this.payID = payID;
	}
	public String getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
	public String getOrderMoney() {
		return orderMoney;
	}
	public void setOrderMoney(String orderMoney) {
		this.orderMoney = orderMoney;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getProductLogo() {
		return productLogo;
	}
	public void setProductLogo(String productLogo) {
		this.productLogo = productLogo;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAdditionalInfo() {
		return additionalInfo;
	}
	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
	public String getMerchant_url() {
		return merchant_url;
	}
	public void setMerchant_url(String merchant_url) {
		this.merchant_url = merchant_url;
	}
	public String getReturn_url() {
		return return_url;
	}
	public void setReturn_url(String return_url) {
		this.return_url = return_url;
	}
	public String getNoticeType() {
		return noticeType;
	}
	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}
	public String getMd5Sign() {
		return md5Sign;
	}
	public void setMd5Sign(String md5Sign) {
		this.md5Sign = md5Sign;
	}
	
	
}
