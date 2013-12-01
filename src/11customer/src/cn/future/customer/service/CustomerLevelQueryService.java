package cn.future.customer.service;

import java.util.List;

import cn.future.customer.dto.DCustomerLevel;
import cn.future.customer.pojo.PCustomerLevel;

public abstract interface CustomerLevelQueryService {
	public int findNextLevelPriority();

	public List<PCustomerLevel> findCustomerLevel();

	public DCustomerLevel transfer(PCustomerLevel level);
}
