package cn.future.pay.util;

public class TenpayConfig {
	public static final String requestGateway="https://gw.tenpay.com/gateway/pay.htm";
	public static final String checkGateway="https://gw.tenpay.com/gateway/simpleverifynotifyid.xml";
	public static final String signType = "MD5";
	public static final String serviceVersion = "1.0";
	public static final String charset = "utf-8";
	public static final String signKeyIndex = "1";
	
	public static final String securityWebUrl = "http://daguanghua.duapp.com";
	public static final String returnUrl="/fs/pay/tenpay_finish.action";
	public static final String notifyUrl="/fs/pay/callBack_tenpay.action";
}
