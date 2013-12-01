package cn.future.customer.util;
/**
 * 用户支付中使用到的编码的信息
 * @author Tony
 *
 */
public class CustomerPayCodeUtil {
	private static final int PAYERROR = -10; //异常错误申请人工服务
	private static final int REQUEST = 0; //提交申请 
	private static final int NEEDPAY = 1; //等待付款
	private static final int PAYFAILED = 9; //支付失败
	private static final int RECEIVED = 10; //收到付款
	
	public static String getPayStatusName(int code){
		String name = "";
		switch(code){
		case PAYERROR: name = "支付异常转人工服务"; break;
		case REQUEST: name = "提交申请"; break;
		case NEEDPAY: name="等待付款"; break;
		case PAYFAILED:name="支付失败";break;
		case RECEIVED:name="收到付款";break;
		}
		return name;
	}
	
	private static final int TENPAY = 2;
	private static final int ALIPAY = 1;
	
	public static String getPayPlatformName(int code){
		String name = "";
		switch(code){
		case TENPAY: name = "财付通"; break;
		case ALIPAY: name = "支付宝"; break;
		}
		return name;
	}
}
