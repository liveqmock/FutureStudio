package cn.future.customer.service;

import cn.future.customer.pojo.PCustomerCredit;

public abstract interface CustomerCreditManageService {
	/**
	 * 新增一个信用记录
	 * @param pojo
	 * @return
	 */
	public PCustomerCredit addCustomerCredit(PCustomerCredit pojo);
}
