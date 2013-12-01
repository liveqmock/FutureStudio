package cn.future.common.service;

import cn.future.customer.dto.DCustomer;

public abstract interface SessionManageService {
	/**
	 * 新一个用户登陆
	 * @param cus
	 */
	public void addSessionCustomer(DCustomer cus);
	
	/**
	 * 用户退出
	 * @param cus
	 */
	public void removeSessionCustomer(DCustomer cus);
	/**
	 * 是否是上次登陆的用户
	 * @param cus
	 * @return
	 */
	public boolean isOld(DCustomer cus);
}
