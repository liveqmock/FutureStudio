package cn.future.finance.service;

import java.util.List;

import cn.future.customer.dto.DCustomerCredit;

public abstract interface ProjectCreditQueryService {
	/**
	 * 通过项目ID，获取信用信息
	 * @param projectId
	 * @return
	 */
	public List<DCustomerCredit> findProjectCreditByProjectId(String projectId);
}
