package cn.future.finance.util;

import cn.future.finance.exception.FinanceProjectStatusCodeError;

public class FinanceCodeUtil {
	//项目状态分类
	public static final int BID_RETURNED=20; //客户已完成还款
	public static final int BID_RETURNING=10; //客户还款中
	public static final int BID_SUCCESS=5; //招满结束
	public static final int BIDDING=1;  //投标中
	public static final int BID_CLOSED=0;//投标关闭
	
	//上面的，外部可见，下面内部可见
	
	public static final int BID_FAILED=-5;//异常关闭 
	public static final int BID_CLOSED_USER=-10; //无理由关闭
	
	public static String getStatusName(int code) throws FinanceProjectStatusCodeError{
		String name="";
		switch(code){
			case BID_RETURNED : name="完成还款";break;
			case BID_RETURNING : name="还款中";break;
			case BID_SUCCESS : name="已满";break;
			case BIDDING : name="投标";break;
			case BID_CLOSED : name="已关闭";break;
			case BID_FAILED : name="异常关闭";break;
			case BID_CLOSED_USER : name="关闭";break;
		}
		if("".equals(name)){
			FinanceProjectStatusCodeError e = new FinanceProjectStatusCodeError("项目状态CODE："+code+"非法");
			throw e;
		}
		return name;
	}
	
	
	//项目 类型分类
	public static final int PLEDGE = 1;  //抵押
	public static final int GUARANTEE = 2;  //担保
	
	public static String getCreditName(int code){
		String name="";
		switch(code){
			case PLEDGE : name="抵押借款";break;
			case GUARANTEE : name="担保借款";break;
		}
		return name;
	}
	
	//还款类型分类
	public static final int MONTH_INTEREST = 1;  //每月反息
	public static final int MONTH_PRINCIPAL_INTEREST = 5;//每月本息还款
	public static final int INTEREST = 10; //项目期末结算
	
	public static String getReturnName(int code){
		String name="";
		switch(code){
			case MONTH_INTEREST : name="每月付息，到期还本";break;
			case MONTH_PRINCIPAL_INTEREST : name="每月等额本息还款";break;
			case INTEREST : name="项目期末结算";break;
		}
		return name;
	}
}
