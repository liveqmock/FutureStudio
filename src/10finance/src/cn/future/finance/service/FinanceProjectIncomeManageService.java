package cn.future.finance.service;

import cn.future.common.exception.NotFindException;
import cn.future.common.exception.SystemBusyException;
import cn.future.finance.exception.FinanceReturnNumberException;
import cn.future.finance.exception.FinanceStatusError;
import cn.future.finance.pojo.PFinanceProjectIncome;
import cn.future.pay.exception.PayBusinessIdUnuquieException;

public interface FinanceProjectIncomeManageService {

	/**
	 * 添加返款
	 * @param pojo
	 * @throws SystemBusyException 
	 * @throws NotFindException 
	 * @throws PayBusinessIdUnuquieException 
	 * @throws FinanceReturnNumberException 
	 */
	public void addIncome(PFinanceProjectIncome pojo) throws PayBusinessIdUnuquieException, NotFindException, SystemBusyException, FinanceReturnNumberException;
	/**
	 * 根据返款计划机型返款
	 * @param pojo
	 * @throws NotFindException 
	 * @throws SystemBusyException 
	 * @throws PayBusinessIdUnuquieException 
	 * @throws FinanceStatusError 
	 * @throws FinanceReturnNumberException 
	 */
	public void addIncomeByPlanId(String returnPlanId) throws NotFindException, PayBusinessIdUnuquieException, SystemBusyException, FinanceStatusError, FinanceReturnNumberException;
}
