package cn.future.customer.service;

import cn.future.customer.pojo.PCustomerLevel;

public abstract interface CustomerLevelManageService {
	//默认新增的都是最大优先级，排序最后 -- 1000 一跳
	public PCustomerLevel addCustomerLevel(PCustomerLevel level);

	public void updateCustomerLevelPriority(String idA,String idB);

	public PCustomerLevel updateCustomerLevel(PCustomerLevel level);
}
