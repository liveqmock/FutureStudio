package cn.future.pay.service;

import java.util.List;

import cn.future.common.exception.NotFindException;
import cn.future.pay.dto.DCashAccount;
import cn.future.pay.dto.DCashAccountDetail;
import cn.future.pay.pojo.PCashAccount;
import cn.future.pay.pojo.PCashAccountDetail;

public interface CashAccountQueryService {
	public abstract DCashAccount transfer(PCashAccount pojo);
	public abstract List<DCashAccount> transfer(List<PCashAccount> pojos);
	/**
	 * 通过ID查找账户， id 同 customer id
	 * @param id
	 * @return
	 * @throws NotFindException
	 */
	public abstract PCashAccount findPCashAccountById(String id)
			throws NotFindException;

	/**
	 * 查询账户交易记录
	 * @param list
	 * @param accountId 可以为空,为空则为查询所有
	 * @param page
	 * @param pageSize
	 * @param operate 可以为null， - or +
	 * @return
	 */
	public abstract int findPCashAccountDetail(List<PCashAccountDetail> list,
			String accountId, int page, int pageSize, String operate);
	/**
	 * Pojo to Dto
	 * @param pojo
	 * @return
	 */
	public abstract DCashAccountDetail detailTransfer(PCashAccountDetail pojo);
	/**
	 * Pojo to Dto
	 * @param pojos
	 * @return
	 */
	public abstract List<DCashAccountDetail> detailTransfer(List<PCashAccountDetail> pojos); 


	/**
	 * 查找现金账户
	 * @param list
	 * @param page
	 * @param pageSize
	 * @param query
	 * @return
	 */
	public abstract int findPCashAccount(List<PCashAccount> list, Integer page, Integer pageSize, String query);
}