package cn.future.customer.service;

import cn.future.customer.pojo.PCustomer;
import cn.future.customer.pojo.PCustomerClientInfo;

public abstract interface CustomerSignLogService {
	/**
	 * 添加登陆日志
	 * @param customer
	 * @param clientInfo
	 */
	public void addLog(PCustomer customer,PCustomerClientInfo clientInfo);

	/**
	 * 验证登陆策略
	 * @param account
	 * @return
	 */
	public void checkSignStrategy(String account);
}
