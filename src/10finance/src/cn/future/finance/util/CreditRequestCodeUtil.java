package cn.future.finance.util;

public class CreditRequestCodeUtil {
	public static final int WAITING = 1;//等待审批
	public static final int EXAMINE = 20; //正在审批
	public static final int REJECTED = -1;//被拒绝
	public static final int PASS = 100;//通过审批
	/**
	 * 获取申请状态的名称
	 * @param code 状态代码
	 * @return 状态名称
	 */
	public static String getName(int code){
		String name = "未知状态";
		switch(code){
		case WAITING : 
			name = "等待审批";
			break;
		case EXAMINE :
			name = "正在审批";
			break;
		case REJECTED :
			name = "被拒绝";
			break;
		case PASS :
			name = "通过审批";
			break;
		}
		return name;
	}
}
