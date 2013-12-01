package cn.future.finance.service;

import cn.future.common.exception.NotFindException;
import cn.future.finance.exception.FinanceProjectStatusCodeError;
import cn.future.finance.pojo.PFinanceProject;

public abstract interface FinanceProjectManageService {
	/**
	 * 添加一个项目
	 * @param pojo
	 * @return
	 */
	public PFinanceProject addFinanceProject(PFinanceProject pojo);
	
	/**
	 * 刷新投标项目状态
	 * @param 项目id
	 * @throws NotFindException 未找到该项目
	 */
	public void refreshFinanceProjectNumberStatus(String id) throws NotFindException;
	
	/**
	 * 更新项目状态
	 * @param id
	 * @param statusCode
	 * @throws NotFindException 
	 * @throws FinanceProjectStatusCodeError 
	 */
	public void updateFinanceProjectStatus(String id,int statusCode) throws NotFindException, FinanceProjectStatusCodeError;
}
