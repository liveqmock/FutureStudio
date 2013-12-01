package cn.future.finance.service.impl;

import cn.future.common.dao.BaseDao;
import cn.future.common.exception.NotFindException;
import cn.future.finance.exception.FinanceStatusError;
import cn.future.finance.pojo.PProjectReturnPlan;
import cn.future.finance.service.ProjectReturnPlanManageService;

public class ProjectReturnPlanManageServiceImpl implements
		ProjectReturnPlanManageService {
	private BaseDao baseDao;
	@Override
	public void deleteReturnPlan(String id) throws NotFindException, FinanceStatusError {
		PProjectReturnPlan pojo = baseDao.findById(PProjectReturnPlan.class, id);
		if(pojo.getStatusCode() != 0){
			FinanceStatusError e = new FinanceStatusError("非计划状态，无法删除");
			throw e;
		}
		baseDao.delete(pojo);
	}
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	@Override
	public void addReturnPlan(PProjectReturnPlan pojo) {
		baseDao.save(pojo);
	}

}
