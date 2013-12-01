package cn.future.finance.util;

public class FinanceBidStatusUtil {
	public static final int BID_AUTO=20; //自动投标
	public static final int BID_MANUAL=10; //手动投标
	public static final int BID_BACK=0; //后台投标
	public static final int BID_CLOSED=-10;//撤销投标
	
	public static String getStatusName(int code){
		String name="";
		switch(code){
			case BID_AUTO : name="自动投标";break;
			case BID_MANUAL : name="手动投标";break;
			case BID_BACK : name="后台投标";break;
			case BID_CLOSED : name="撤销投标";break;
		}
		return name;
	}
}
