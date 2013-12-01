package cn.future.customer.service;

import java.util.List;

import cn.future.customer.dto.DCustomerCredit;
import cn.future.customer.pojo.PCustomerCredit;

public abstract interface CustomerCreditQueryService {
	/**
	 * 查询用户的信用抵押
	 * @param customerId
	 * @return
	 */
	public List<PCustomerCredit> findCustomerCredit(String customerId, int page, int pageSize);
	
	/**
	 * 通过ID查询信用抵押
	 * @param ids
	 * @return
	 */
	public List<PCustomerCredit> findCustomerCreditByIds(List<String> ids);
	/**
	 * Pojo 2 Dto
	 * @param pojos
	 * @return
	 */
	public List<DCustomerCredit> transfer(List<PCustomerCredit> pojos);
	/**
	 * Pojo 2 Dto
	 * @param pojo
	 * @return
	 */
	public DCustomerCredit transfer(PCustomerCredit pojo);
}
