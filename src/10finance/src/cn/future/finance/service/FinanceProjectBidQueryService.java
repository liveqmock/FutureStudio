package cn.future.finance.service;

import java.util.List;

import cn.future.finance.dto.DFinanceProjectBidDetail;
import cn.future.finance.pojo.PFinanceProjectBidDetail;

public abstract interface FinanceProjectBidQueryService {
	/**
	 * 对象转换
	 * @param pojos
	 * @return
	 */
	public List<DFinanceProjectBidDetail> transfer(List<PFinanceProjectBidDetail> pojos, boolean security);
	/**
	 * 对象转换
	 * @param pojos
	 * @return
	 */
	public DFinanceProjectBidDetail transfer(PFinanceProjectBidDetail pojo, boolean security);
	/**
	 * 查找项目的投标记录
	 * @param financeProjectId
	 * @return
	 */
	public int findFinanceProjectBidDetails(List<PFinanceProjectBidDetail> list, String financeProjectId, Integer page, Integer pageSize);
	/**
	 * 查找用户的投标记录
	 * @param list 已经初始化List 列表，用于添加当前记录
	 * @param customerId 会员ID
	 * @param page 页面
	 * @param pageSize 抓取数量
	 * @return
	 */
	public int findCustomerBidDetails(List<PFinanceProjectBidDetail> list, String customerId, Integer page, Integer pageSize);
	/**
	 * 查找用户的投标记录，记录中，没有完全结算的项目
	 * @param list
	 * @param customerId
	 * @param page
	 * @param pageSize
	 * @return 记录总数
	 */
	public int findCustomerUnreturnedFinanceProjectBidDetails(List<PFinanceProjectBidDetail> list, String customerId, Integer page, Integer pageSize);
	/**
	 * 查找用户尚未退还的现金数,单位：分.
	 * 目前只有抵押项目，所以只查询一个集合。以后可能有多个项目
	 * @param customerId
	 * @return 金额
	 */
	public long findCustomerUnreturnedNumber(String customerId);
}
