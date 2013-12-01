package cn.future.finance.service;

import cn.future.common.exception.NotFindException;
import cn.future.finance.pojo.PCreditRequest;

public interface CreditRequestManageService {
	/**
	 * 添加申请
	 * @param pojo
	 * @param list
	 * @return
	 */
	public PCreditRequest addPCreditRequest(PCreditRequest pojo);
	/**
	 * 修改申请状态
	 * @param id
	 * @param status
	 * @return
	 * @throws NotFindException 
	 */
	public PCreditRequest updatePCreditRequestStatus(String id, int status) throws NotFindException;
	/**
	 * 添加项目备注信息
	 * @param id
	 * @param comments
	 * @param approvalComments
	 * @return
	 * @throws NotFindException 
	 */
	public PCreditRequest addCreditRequestComments(String id, String comments, String approvalComments) throws NotFindException;
}
