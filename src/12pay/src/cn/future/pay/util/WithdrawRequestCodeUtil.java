package cn.future.pay.util;

public class WithdrawRequestCodeUtil {
	public static int REQUESTING = -10;
	public static int SECURITY_VALIDATED = 0;
	public static int PAY_FAIL = 5;
	public static int PAY_ALREADY =10;
	
	public static String getRequestCodeName(int code){
		String s = "未知";
		switch(code){
		case -10:
			s = "已提交，等验证";
			break;
		case 0:
			s = "等待处理";
			break;
		case 10:
			s = "已转款";
			break;
		}
		return s;
	}
}
