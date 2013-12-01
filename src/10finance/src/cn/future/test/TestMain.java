package cn.future.test;

import cn.future.finance.util.FinanceMoneyUtil;

public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s = FinanceMoneyUtil.formatThreeSplitFen(1);
		System.out.println(s);
	}

}
