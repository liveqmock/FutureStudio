package cn.future.pay.util;

public class BankCodeUtil {
	public static String getBankName(String bankName){
		if("zs".equals(bankName)){
			//招行
			return "招商银行";
		}else if("gs".equals(bankName)){
			//工行
			return "工商银行";
		}else if("js".equals(bankName)){
			//建行
			return "建设银行";
		}else if("shpdfz".equals(bankName)){
			//上海浦东发展
			return "上海浦东发展银行";
		}else if("ny".equals(bankName)){
			//农业银行
			return "农业银行";
		}else if("ms".equals(bankName)){
			//中国民生
			return "民生银行";
		}else if("xy".equals(bankName)){
			//兴业银行
			return "兴业银行";
		}else if("jt".equals(bankName)){
			//交通
			return "交通银行";
		}else if("gd".equals(bankName)){
			//光大
			return "光大银行";
		}else if("zg".equals(bankName)){
			//中国
			return "中国银行";
		}else if("bj".equals(bankName)){
			//北京
			return "北京银行";
		}else if("beady".equals(bankName)){
			//bea东亚
			return "BEA东亚银行";
		}else if("pa".equals(bankName)){
			//平安
			return "平安银行";
		}else if("gf".equals(bankName)){
			//广发
			return "广发银行";
		}else if("yz".equals(bankName)){
			//邮政储蓄
			return "邮政储蓄银行";
		}else if("zx".equals(bankName)){
			//中信
			return "中信银行";
		}else{
			return "未选择银行";
		}
	}
}
