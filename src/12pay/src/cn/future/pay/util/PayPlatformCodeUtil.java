package cn.future.pay.util;

public class PayPlatformCodeUtil {
	public static final int ALIPAY = 100;
	public static final int TENPAY = 200;
	public static final int BAOPAY = 300;
	
	public static String getPlatformName(int code){
		String name = "";
		switch(code){
		case ALIPAY:
			name = "支付宝";
			break;
		case TENPAY:
			name = "财付通";
			break;
		case BAOPAY:
			name = "宝付";
			break;
		}
		
		return name;
	}
}
