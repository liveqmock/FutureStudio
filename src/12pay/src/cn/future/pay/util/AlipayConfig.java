package cn.future.pay.util;

public class AlipayConfig {
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static final String partner = "";
	// 商户的私钥
	public static final String key = "";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static final String charset = "utf-8";
	
	// 签名方式 不需修改
	public static final String signType = "MD5";
	//卖家支付宝账户
	public static final String sellerEmail = "";
}
