package cn.future.finance.service;

import java.util.Date;
import java.util.List;

import cn.future.common.exception.NotFindException;
import cn.future.finance.dto.DProjectReturnPlan;
import cn.future.finance.pojo.PProjectReturnPlan;

public abstract interface ProjectReturnPlanQueryService {
	/**
	 * 查询项目的返款计划
	 * @param projectId
	 * @return
	 */
	public List<PProjectReturnPlan> findProjectReturnPlan(String projectId);

	/**
	 * pojo to dto
	 * @param p
	 * @return
	 */
	public DProjectReturnPlan transfer(PProjectReturnPlan p);
	/**
	 * pojos to dtos
	 * @param ps
	 * @return
	 */
	public List<DProjectReturnPlan> transfer(List<PProjectReturnPlan> ps);
	
	/**
	 * 通过ID查找返款计划
	 * @param id
	 * @return
	 * @throws NotFindException 
	 */
	public PProjectReturnPlan findById(String id) throws NotFindException;
	/**
	 * 查询所有的返款计划
	 * @param list 添加队列
	 * @param statusCode 等于某code
	 * @param page
	 * @param pageSize
	 * @param start 计划 —— 开始时间
	 * @param end  计划 —— 结束时间
	 * @param queryComments
	 * @param isDesc 是否时间降序
	 * @return
	 */
	public int findAll(List<PProjectReturnPlan> list, Integer statusCode, Integer page, Integer pageSize,
			Date start, Date end, String queryComments, boolean isDesc);
	/**
	 * 查询指定用户的返款计划
	 * @param list
	 * @param customerId
	 * @param statusCode
	 * @param page
	 * @param pageSize
	 * @param start
	 * @param end
	 * @param queryComments
	 * @param isDesc
	 * @return
	 */
	public int findAll(List<PProjectReturnPlan> list, List<String> projectIds, Integer statusCode, Integer page, Integer pageSize,
			Date start, Date end, String queryComments, boolean isDesc);
}
