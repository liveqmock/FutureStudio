package cn.future.pay.service;

import cn.future.common.exception.NotFindException;
import cn.future.pay.exception.PayBusinessIdUnuquieException;
import cn.future.pay.exception.PayOverSpendException;
import cn.future.common.exception.SystemBusyException;
import cn.future.pay.pojo.PCashAccount;
import cn.future.pay.pojo.PCashAccountDetail;

public interface CashAccountManageService {

	/**
	 * 添加一个现金账户
	 * @param ca
	 */
	public abstract PCashAccount addCashAccount(PCashAccount ca);

	/**
	 * 
	 * @param accountId
	 * @param businessId
	 * @param inNumber
	 * @param title
	 * @param content
	 * @param comments
	 * @param tagCode
	 * @return
	 * @throws NotFindException
	 * @throws PayBusinessIdUnuquieException
	 * @throws PaySystemBusyException
	 */
	public abstract PCashAccountDetail createCashAccountDetailIn(
			String accountId, String businessId, long inNumber, String title,
			String content, String comments, String tagCode)
			throws NotFindException, PayBusinessIdUnuquieException,
			SystemBusyException;

	/**
	 * 
	 * @param accountId
	 * @param businessId
	 * @param inNumber
	 * @param title
	 * @param content
	 * @param comments
	 * @param tagCode
	 * @return
	 * @throws NotFindException
	 * @throws PayOverSpendException
	 * @throws PayBusinessIdUnuquieException
	 * @throws PaySystemBusyException
	 */
	public abstract PCashAccountDetail createCashAccountDetailOut(
			String accountId, String businessId, long outNumber, String title,
			String content, String comments, String tagCode)
			throws NotFindException, PayOverSpendException,
			PayBusinessIdUnuquieException, SystemBusyException;

	/**
	 * 
	 * @param businessId
	 * @throws PayBusinessIdUnuquieException
	 */
	public abstract void checkBusinessIdUnique(String businessId)
			throws PayBusinessIdUnuquieException;

	
	/**
	 * 确认会员账户存在，如果不存在，则创建一个
	 * @param id
	 */
	public abstract void validateCustomerCashAccountExist(String id);
}