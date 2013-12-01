package cn.future.finance.service;

import java.util.List;

import cn.future.common.exception.NotFindException;
import cn.future.finance.pojo.PFinanceProject;
import cn.future.finance.dto.DFinanceProject;

public abstract interface FinanceProjectQueryService {
	/**
	 * 对象转换
	 * @param pojo
	 * @param transMode : 
	 * 		0（默认）数据转换；   1扩展用户信息；   
	 * 		2扩展客户信息；    4扩展投标信息
	 * 		5模糊关键数据		6初始化项目使用的信用信息
	 * @return
	 */
	public List<DFinanceProject> transfer(List<PFinanceProject> pojos, int[] transModes);
	/**
	 * 对象转换
	 * @param pojo
	 * @return
	 */
	public List<DFinanceProject> transfer(List<PFinanceProject> pojos);
	/**
	 * 对象转换
	 * @param pojo
	 * @param transMode : 
	 * 		0（默认）数据转换；   1扩展用户信息；   
	 * 		2扩展客户信息；    4扩展投标信息
	 * 		5模糊关键数据		6初始化项目使用的信用信息
	 * @return
	 */
	public DFinanceProject transfer(PFinanceProject pojo, int[] transModes);
	/**
	 * 对象转换
	 * @param pojo
	 * @return
	 */
	public DFinanceProject transfer(PFinanceProject pojo);
	/**
	 * 查找投资项目
	 * @param startStatus
	 * @param page
	 * @param pageSize
	 * @param query
	 * @return
	 */
	public int findFinanceProject(List<PFinanceProject> list,Integer projectType, Integer returnType, Integer startStatus, int page, int pageSize, String query);
	/**
	 * 通过ID查找项目
	 * @param id
	 * @return
	 * @throws NotFindException 
	 */
	public PFinanceProject findFinanceById(String id) throws NotFindException;

	/**
	 * 查询用户的历史借款记录， 状态大于 bidding的项目
	 * @param list
	 * @param customerId
	 * @param page
	 * @param pageSize
	 * @return 记录数
	 */
	public int findCustomerHistoryRequestFinanceProject(List<PFinanceProject> list, String customerId, Integer page, Integer pageSize);
	
	/**
	 * 查找用户投标过的项目
	 * @param list
	 * @param customerId
	 * @param page
	 * @param pageSize
	 * @param isFinish  1已经完成的项目， 0未完成的项目， null 不过滤
	 * @return
	 */
	public int findCustomerHistoryBidFinanceProject(List<PFinanceProject> list, String customerId, Integer page, Integer pageSize, Integer isFinish);
	
	
}