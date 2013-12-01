package cn.future.finance.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.future.common.dao.BaseDao;
import cn.future.common.exception.NotFindException;
import cn.future.common.exception.SystemBusyException;
import cn.future.finance.dto.DFinanceProjectIncome;
import cn.future.finance.exception.FinanceReturnNumberException;
import cn.future.finance.exception.FinanceStatusError;
import cn.future.finance.pojo.PFinanceProject;
import cn.future.finance.pojo.PFinanceProjectBidDetail;
import cn.future.finance.pojo.PFinanceProjectIncome;
import cn.future.finance.pojo.PProjectReturnPlan;
import cn.future.finance.service.FinanceProjectBidManageService;
import cn.future.finance.service.FinanceProjectBidQueryService;
import cn.future.finance.service.FinanceProjectIncomeManageService;
import cn.future.finance.service.FinanceProjectQueryService;
import cn.future.finance.service.ProjectReturnPlanQueryService;
import cn.future.pay.exception.PayBusinessIdUnuquieException;
import cn.future.pay.service.CashAccountManageService;

public class FinanceProjectIncomeManageServiceImpl implements
		FinanceProjectIncomeManageService {
	private BaseDao baseDao;
	private ProjectReturnPlanQueryService projectReturnPlanQueryService;
	private FinanceProjectQueryService financeProjectQueryService;
	private FinanceProjectBidQueryService financeProjectBidQueryService;
	private FinanceProjectBidManageService financeProjectBidManageService;
	private CashAccountManageService cashAccountManageService;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	public void setFinanceProjectBidManageService(
			FinanceProjectBidManageService financeProjectBidManageService) {
		this.financeProjectBidManageService = financeProjectBidManageService;
	}

	public void setCashAccountManageService(
			CashAccountManageService cashAccountManageService) {
		this.cashAccountManageService = cashAccountManageService;
	}

	public void setFinanceProjectBidQueryService(
			FinanceProjectBidQueryService financeProjectBidQueryService) {
		this.financeProjectBidQueryService = financeProjectBidQueryService;
	}

	public void setFinanceProjectQueryService(
			FinanceProjectQueryService financeProjectQueryService) {
		this.financeProjectQueryService = financeProjectQueryService;
	}
	public void setProjectReturnPlanQueryService(
			ProjectReturnPlanQueryService projectReturnPlanQueryService) {
		this.projectReturnPlanQueryService = projectReturnPlanQueryService;
	}


	@Override
	public void addIncome(PFinanceProjectIncome pojo) throws PayBusinessIdUnuquieException, NotFindException, SystemBusyException, FinanceReturnNumberException{
		DFinanceProjectIncome dto = new DFinanceProjectIncome(pojo);
		String title = dto.getTypeName()+" 收入";
		String content = pojo.getFinanceProjectId()+":"+pojo.getCurrentMonth()+"/"+pojo.getProjectMonth()+":"+pojo.getPercentShow();
		String comments = "";
		cashAccountManageService.createCashAccountDetailIn(pojo.getCustomerId(), pojo.getId(), pojo.getIncomeNumber(), title, content, comments, "FinanceProjectIncome");
		if(pojo.getTypeCode()==1){
			//本金，需要修改bid detail 的信息
			financeProjectBidManageService.addBidDetailsAlreadyReturn(pojo.getFinanceProjectBidId(), pojo.getIncomeNumber());
		}
		baseDao.save(pojo);
	}
	@Override
	public void addIncomeByPlanId(String returnPlanId) throws NotFindException, PayBusinessIdUnuquieException, SystemBusyException, FinanceStatusError, FinanceReturnNumberException {
		PProjectReturnPlan plan = projectReturnPlanQueryService.findById(returnPlanId);
		if(plan.getStatusCode()!=PProjectReturnPlan.RETURN_PLAN){
			FinanceStatusError e = new FinanceStatusError("还款计划，非等待状态，无法执行");
			throw e;
		}
		PFinanceProject project = financeProjectQueryService.findFinanceById(plan.getProjectId());
		List<PFinanceProjectBidDetail> bids = new ArrayList<PFinanceProjectBidDetail>();
		financeProjectBidQueryService.findFinanceProjectBidDetails(bids, project.getId(), null, null);
		
		for(PFinanceProjectBidDetail bid : bids){
			PFinanceProjectIncome p = new PFinanceProjectIncome(plan, bid, bid.getCustomerId());
			this.addIncome(p);
		}
		
		plan.setStatusCode(PProjectReturnPlan.RETURN_RETURNED);
		baseDao.save(plan);
	}

}
