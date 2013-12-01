package cn.future.finance.service;

import java.util.List;

import cn.future.finance.dto.DCreditRequest;
import cn.future.finance.pojo.PCreditRequest;

public interface CreditRequestQueryService {
	/**
	 * 查询借款申请
	 * @param userId 用户ID
	 * @param position 用户类型c OR u 
	 * @param page 
	 * @param pageSize
	 * @return
	 */
	public int findCreditRequest(List<PCreditRequest> list,String userId, String position, Integer page, Integer pageSize);
	/**
	 * pojo to dtos
	 * @param pojos
	 * @return
	 */
	public List<DCreditRequest> transfer(List<PCreditRequest> pojos);
	/**
	 * pojo to dto
	 * @param pojo
	 * @return
	 */
	public DCreditRequest transfer(PCreditRequest pojo);
}
