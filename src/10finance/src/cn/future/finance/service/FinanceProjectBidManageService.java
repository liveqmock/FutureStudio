package cn.future.finance.service;

import cn.future.common.exception.NotFindException;
import cn.future.common.exception.SystemBusyException;
import cn.future.customer.dto.DCustomer;
import cn.future.finance.exception.FinanceProjectOverBidException;
import cn.future.finance.exception.FinanceProjectStatusException;
import cn.future.finance.exception.FinanceReturnNumberException;
import cn.future.pay.exception.PayBusinessIdUnuquieException;
import cn.future.pay.exception.PayOverSpendException;

public interface FinanceProjectBidManageService {

	/**
	 * 增加投标
	 * @param projectId
	 * @param customer
	 * @param cashNumber
	 * @param statusCode
	 * @throws PayOverSpendException 账户金额不足
	 * @throws PayBusinessIdUnuquieException 支付系统业务ID重复
	 * @throws SystemBusyException 系统忙
	 * @throws NotFindException 项目不存在
	 * @throws FinanceProjectOverBidException 投标金额超过招标剩余金额
	 * @throws FinanceProjectStatusException 项目停止投标
	 */
	public abstract void addCustomerPFinanceProjectBidDetail(String projectId,
			DCustomer customer, long cashNumber, int statusCode) throws PayOverSpendException,
			PayBusinessIdUnuquieException, SystemBusyException,
			NotFindException, FinanceProjectOverBidException, FinanceProjectStatusException;

	/**
	 * 增加投标退款信息
	 * @param id
	 * @param returnNumber
	 * @throws NotFindException 
	 * @throws FinanceReturnNumberException 
	 */
	public abstract void addBidDetailsAlreadyReturn(String id, long returnNumber) throws NotFindException, FinanceReturnNumberException;
}