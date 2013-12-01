package cn.future.finance.service;

import java.util.Date;
import java.util.List;

import cn.future.finance.dto.DFinanceProjectIncome;
import cn.future.finance.pojo.PFinanceProjectIncome;

public abstract interface FinanceProjectIncomeQueryService {
	/**
	 * 查询收入记录
	 * @param list
	 * @param cusId
	 * @param projectId
	 * @param bidId
	 * @param typeCode 收入类型   1本金 2利息
	 * @param start
	 * @param end
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public int findFinanceProjectIncome(List<PFinanceProjectIncome> list, String cusId,
			String projectId, String bidId, Integer typeCode, Date start, Date end,
			Integer page, Integer pageSize);
	/**
	 * pojo to dto
	 * @param pojo
	 * @return
	 */
	public DFinanceProjectIncome transfer(PFinanceProjectIncome pojo);
	/**
	 * pojos to dtos
	 * @param pojo
	 * @return
	 */
	public List<DFinanceProjectIncome> transfer(List<PFinanceProjectIncome> pojos);
	/**
	 * 查询总收入
	 * @param cusId
	 * @param projectId
	 * @return
	 */
	public long findSumIncome(String cusId, String projectId);
}
