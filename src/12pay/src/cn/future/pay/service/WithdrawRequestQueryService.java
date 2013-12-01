package cn.future.pay.service;

import java.util.List;

import cn.future.pay.dto.DWithdrawRequest;
import cn.future.pay.pojo.PWithdrawRequest;

public interface WithdrawRequestQueryService {
	/**
	 * 查询提现记录
	 * @param list 
	 * @param userId 用户id
	 * @param status 提现状态 -10 提交了申请   0等待转款   10 已经付款
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public int find(List<PWithdrawRequest> list, String userId, Integer status, Integer page, Integer pageSize);
	/**
	 * pojo to dto
	 * @param pojos
	 * @return
	 */
	public List<DWithdrawRequest> transfer(List<PWithdrawRequest> pojos);
	/**
	 * pojo to dto
	 * @param pojo
	 * @return
	 */
	public DWithdrawRequest transfer(PWithdrawRequest pojo);
}
