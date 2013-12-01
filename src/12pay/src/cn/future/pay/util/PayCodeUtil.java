package cn.future.pay.util;

public class PayCodeUtil {
	//status code
	public static final int PAY_ERROR = -10; //支付异常
	public static final int PAY_REQUEST = 0; //申请充值
	public static final int PAY_ING = 1; //支付成功
	public static final int PAY_WAIT = 2; //等待支付结果
	public static final int PAY_FAIL = 3; //支付失败
	
	public static final String getPayStatusName(int code){
		String s = "未知";
		switch(code){
		case PAY_ERROR:
			s = "支付异常";
			break;
		case PAY_REQUEST:
			s = "申请充值";
			break;
		case PAY_ING:
			s = "还未支付";
			break;
		case PAY_WAIT:
			s = "等待支付结果";
			break;
		case PAY_FAIL:
			s = "支付失败";
			break;
		}
		return s;
	}
	// platform code
	public static final int UNKNOW_CODE = 0;
	public static final int TENPAY_CODE = 1;
	public static final int ALIPAY_CODE = 2;
	
	public static final String getPlatformName(int code){
		String s = "未知";
		switch(code){
		case TENPAY_CODE:
			s = "财付通";
			break;
		case ALIPAY_CODE:
			s = "支付宝";
			break;
		case UNKNOW_CODE:
			s = "无";
			break;
		}
		return s;
	}
}
