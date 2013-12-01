package cn.future.pay.service.tenpay.impl;

import cn.future.common.service.impl.ConfigHelperImpl;
import cn.future.pay.pojo.PPayRequest;
import cn.future.pay.pojo.TenpayRequest;
import cn.future.pay.service.tenpay.TenpayRequestFactory;
import cn.future.util.TimeUtil;
import cn.future.pay.util.TenpayConfig;

public class TenpayRequestFactoryImpl implements TenpayRequestFactory{
	public static int COUNT = 100000;
	private String securityUrl = ConfigHelperImpl.getProperty("securityUrl", "");
	private TenpayRequest tenpay ;
	/**
	 * 会初始化默认的 partner id key name 信息
	 */
	public TenpayRequestFactoryImpl(){
		//创建实例
		this.tenpay = new TenpayRequest();
		
		tenpay.setPartner(ConfigHelperImpl.getProperty("tenpayId", "")); //商户号
		tenpay.setPartnerName(ConfigHelperImpl.getProperty("companyRealName", "")); //商户名
		tenpay.setPartnerKey(ConfigHelperImpl.getProperty("tenpayKey", ""));
		
		//默认交易类型
		tenpay.setBank_type("DEFAULT");//银行类型(中介担保时此参数无效)
		tenpay.setTrade_mode("1");//1即时到账(默认)，2中介担保，
		//平台信息
		tenpay.setSign_type(TenpayConfig.signType);
		tenpay.setSign_key_index(TenpayConfig.signKeyIndex);
		tenpay.setService_version(TenpayConfig.serviceVersion);
		tenpay.setInput_charset(TenpayConfig.charset);
		//default url
		this.initUrlInfo();
		//以下信息暂不初始化

//		private String agentid;                     //平台ID
//		private String agent_type;                  //代理模式，0无代理(默认)，1表示卡易售模式，2表示网店模式
//		private String seller_id;                   //卖家商户号，为空则等同于partner
	}
	@Override
	public TenpayRequest getTenpayRequest(){
		return this.tenpay;
	}
//	@Override
//	public TenpayRequest getDemandTenpayRequest(){
//		this.setDemandPay();
//		return this.tenpay;
//	}

//	@Override
//	public TenpayRequestFactory initUrlInfo(String gateUrl, String returnUrl, String notifiUrl){
//		String return_url=PayConfig.domainUrl+returnUrl; //前台回访
//		String notify_url=PayConfig.domainUrl+notifiUrl; //后台回调
//		this.tenpay.setReturn_url(return_url);
//		this.tenpay.setNotify_url(notify_url);
//		this.tenpay.setGateway(gateUrl);
//		return this;
//	}
	/**
	 * 默认的回调地址
	 * "${domain}/${tenpayCallBack}?id=id"
	 */
	public void initUrlInfo(){
		String return_url=securityUrl+TenpayConfig.returnUrl; //前台回访
		String notify_url=securityUrl+TenpayConfig.notifyUrl; //后台回调
		this.tenpay.setReturn_url(return_url);
		this.tenpay.setNotify_url(notify_url);
		this.tenpay.setGateway(TenpayConfig.requestGateway);
	}
	/**
	 * 指定交易为担保交易。
	 * 取消银行类型设置，修改交易模式为2
	 */
	public void setDemandPay(){
		this.tenpay.setBank_type(null);//银行类型(中介担保时此参数无效)
		this.tenpay.setTrade_mode("2");//1即时到账(默认)，2中介担保，
	}
	@Override
	public TenpayRequestFactory initPayInfo(PPayRequest p){
		this.tenpay.setOrderId(p.getId());

		this.tenpay.setOut_trade_no(p.getId());
		this.tenpay.setProduct_fee(p.getProductNumber());
		this.tenpay.setTransport_fee(p.getTransNumber());
		this.tenpay.setTotal_fee(p.getProductNumber()+p.getTransNumber());
		
		this.tenpay.setTransport_desc(p.getTransportDesc());
		this.tenpay.setTrans_type(p.getTransType());
		this.tenpay.setBody(p.getContent());
		this.tenpay.setSubject(p.getTitle());
		this.tenpay.setSpbill_create_ip(p.getUserIp());
		this.tenpay.setFee_type("1");  //财付通暂时只有RMB类型

		//业务参数
		
		this.tenpay.setTime_start(TimeUtil.dateToYMDHMSString(p.getCreateDate()));
		this.tenpay.setTime_expire(TimeUtil.dateToYMDHMSString(p.getDeadDate()));
		//
		this.tenpay.setGoods_tag("");
		return this;
	}

//	@Override
//	public TenpayRequestFactory initCustomerTenpayAccount(String tenpayAccount){
//		this.tenpay.setBuyer_id(tenpayAccount);
//		return this;
//	}
}
