package cn.future.customer.service;

import cn.future.customer.pojo.PCustomerDetailInfo;

/**
 * 客户详细信息管理服务
 * @author Tony
 *
 */
public abstract interface CustomerDetailInfoManageService {
	/**
	 * 更新客户详细信息
	 * @param p
	 * @return
	 */
	public PCustomerDetailInfo save(PCustomerDetailInfo p);
}
