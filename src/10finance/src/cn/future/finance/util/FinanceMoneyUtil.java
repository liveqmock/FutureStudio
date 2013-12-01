package cn.future.finance.util;

public class FinanceMoneyUtil {
	/**
	 * 按照千分位显示金额
	 * @param cash 单位：元，带两位小数
	 * @return
	 */
	public static String formatThreeSplit( double cash ){
		String num=cash+"";
        num = num.replaceAll(",", ""); // 去掉所有逗号
        java.text.DecimalFormat df = new java.text.DecimalFormat("###,###,###,###,###.##");
        return df.format( Double.parseDouble( num ) );
    }
	/**
	 * 将分进行千分位显示
	 * @param cash 单位：分
	 * @return
	 */
	public static String formatThreeSplitFen(long cash){
		double c = ((double)cash)/100;
		return FinanceMoneyUtil.formatThreeSplit(c);
	}
}
