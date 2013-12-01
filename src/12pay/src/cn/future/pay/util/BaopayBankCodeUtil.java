package cn.future.pay.util;

public class BaopayBankCodeUtil {
	/**
	 * 返回支持银行的编号
	 * @param bankName
	 * @return
	 */
	public static String getBaoBankId(String bankName){
		
		if("zs".equals(bankName)){
			//招行
			return "1001";
		}else if("gs".equals(bankName)){
			//工行
			return "1002";
		}else if("js".equals(bankName)){
			//建行
			return "1003";
		}else if("shpdfz".equals(bankName)){
			//上海浦东发展
			return "1004";
		}else if("ny".equals(bankName)){
			//农业银行
			return "1005";
		}else if("ms".equals(bankName)){
			//中国民生
			return "1006";
		}else if("xy".equals(bankName)){
			//兴业银行
			return "1009";
		}else if("jt".equals(bankName)){
			//交通
			return "1020";
		}else if("gd".equals(bankName)){
			//光大
			return "1022";
		}else if("zg".equals(bankName)){
			//中国
			return "1026";
		}else if("bj".equals(bankName)){
			//北京
			return "1032";
		}else if("beady".equals(bankName)){
			//bea东亚
			return "1033";
		}else if("pa".equals(bankName)){
			//平安
			return "1035";
		}else if("gf".equals(bankName)){
			//广发
			return "1036";
		}else if("yz".equals(bankName)){
			//邮政储蓄
			return "1038";
		}else if("zx".equals(bankName)){
			//中信
			return "1039";
		}else{
			return "1000";
		}
	}
}
