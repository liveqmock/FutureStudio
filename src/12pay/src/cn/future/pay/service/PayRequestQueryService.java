package cn.future.pay.service;

import java.util.Date;
import java.util.List;

import cn.future.pay.dto.DPayRequest;
import cn.future.pay.pojo.PPayRequest;

public abstract interface PayRequestQueryService {
	/**
	 * 查询充值记录
	 * @param list
	 * @param page
	 * @param pageSize
	 * @param customerId
	 * @param start
	 * @param end
	 * @param status 1表示成功
	 * @return
	 */
	public int findPayRequest(List<PPayRequest> list, int page, int pageSize, String customerId, Date start, Date end, Integer status);
	/**
	 * pojo to dto
	 * @param pojo
	 * @return
	 */
	public DPayRequest transfer(PPayRequest pojo);
	/**
	 * pojo to dto
	 * @param pojo
	 * @return
	 */
	public List<DPayRequest> transfer(List<PPayRequest> pojos);
}
