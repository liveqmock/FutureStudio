package cn.future.finance.service;

import cn.future.common.exception.NotFindException;
import cn.future.finance.exception.FinanceStatusError;
import cn.future.finance.pojo.PProjectReturnPlan;

public interface ProjectReturnPlanManageService {
	/**
	 * 删除某个还款计划
	 * @param id
	 * @throws NotFindException 
	 * @throws FinanceStatusError 
	 */
	public void deleteReturnPlan(String id) throws NotFindException, FinanceStatusError;
	/**
	 * 添加一个还款计划
	 * @param pojo
	 */
	public void addReturnPlan(PProjectReturnPlan pojo);
}
