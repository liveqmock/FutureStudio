package cn.future.customer.service;

import java.util.List;

import cn.future.common.exception.NotFindException;
import cn.future.customer.dto.DCustomerDetailInfo;
import cn.future.customer.pojo.PCustomerDetailInfo;

/**
 * 客户详细信息查询服务
 * @author Tony
 *
 */
public abstract interface CustomerDetailInfoQueryService {
	/**
	 * 通过ID查询
	 * @param id
	 * @return
	 * @throws NotFindException 
	 */
	public PCustomerDetailInfo findById(String id) throws NotFindException;
	
	public DCustomerDetailInfo transfer(PCustomerDetailInfo p);
	public List<DCustomerDetailInfo> transfer(List<PCustomerDetailInfo> ps);
}
