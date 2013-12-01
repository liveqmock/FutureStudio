package cn.future.pay.service.tenpay;

import cn.future.pay.pojo.PPayRequest;
import cn.future.pay.pojo.TenpayRequest;

public abstract interface TenpayRequestFactory {
//	/**
//	 * 特殊的回调地址：
//	 * @param gateUrl "http://***.htm"
//	 * @param returnUrl  "/special.action?xx=param"
//	 * @param notifiUrl
//	 */
//	public TenpayRequestFactory initUrlInfo(String gateUrl,String returnUrl, String notifiUrl);
	/**
	 * 获取即使到账交易类型
	 */
	public TenpayRequest getTenpayRequest();
//	/**
//	 * 获取担保交易类型
//	 */
//	public TenpayRequest getDemandTenpayRequest();
	/**
	 * 通过充值对象创建基础数据
	 * @param p
	 * @return
	 */
	public TenpayRequestFactory initPayInfo(PPayRequest p);
//	/**
//	 * 指定的财付通账户交易
//	 * @param tenpayAccount
//	 * @return
//	 */
//	public TenpayRequestFactory initCustomerTenpayAccount(String tenpayAccount);
}
