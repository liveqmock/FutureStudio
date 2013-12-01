package cn.future.pay.service;

import java.util.List;

import cn.future.common.exception.NotFindException;
import cn.future.pay.dto.DWithdrawAccount;
import cn.future.pay.pojo.PWithdrawAccount;

public abstract interface WithdrawAccountQueryService {
	/**
	 * 查询用户的取现账户
	 * @param userId 用户ID, 可以为null， 则查询全部
	 * @param statusCode 账户状态 0， 1, null则取全部
	 * @return
	 */
	public List<PWithdrawAccount> findAll(String userId, Integer statusCode);
	/**
	 * 查询单个账户
	 * @param id 账户ID
	 * @return
	 * @throws NotFindException 
	 */
	public PWithdrawAccount findById(String id) throws NotFindException;
	/**
	 * pojo to dto
	 * @param list
	 * @return
	 */
	public List<DWithdrawAccount> transfer(List<PWithdrawAccount> list);
	/**
	 * pojo to dto
	 * @param list
	 * @return
	 */
	public DWithdrawAccount transfer(PWithdrawAccount pojo);
}
