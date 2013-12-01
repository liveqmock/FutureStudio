package cn.future.finance.action;

import java.util.ArrayList;
import java.util.List;

import cn.future.common.action.BaseAction;
import cn.future.common.exception.NotFindException;
import cn.future.finance.dto.DFinanceProject;
import cn.future.finance.dto.DProjectReturnPlan;
import cn.future.finance.pojo.PFinanceProject;
import cn.future.finance.pojo.PProjectReturnPlan;
import cn.future.finance.service.FinanceProjectQueryService;
import cn.future.finance.service.ProjectReturnPlanQueryService;

public class ReturnPlanQueryAction extends BaseAction{

	private static final long serialVersionUID = 7437928946647884393L;

	private ProjectReturnPlanQueryService projectReturnPlanQueryService;
	private FinanceProjectQueryService financeProjectQueryService;
	//set
	private String id;
	private int statusCode;//等于Code
	
	//set get
	private int page=1;
	//get
	private int pageSize=20;
	private int count;
	private List<DProjectReturnPlan> projectReturnPlans;
	private DFinanceProject project;
	/**
	 * 查询所有的返款计划
	 * @return
	 */
	public String list(){
		boolean isDesc = true;
		if(0==statusCode){
			isDesc = false;
		}
		List<PProjectReturnPlan> list = new ArrayList<PProjectReturnPlan>();
		count = projectReturnPlanQueryService.findAll(list, new Integer(statusCode),
				new Integer(page), new Integer(pageSize), 
				null, null, null, isDesc);
		projectReturnPlans = projectReturnPlanQueryService.transfer(list);
		return SUCCESS;
	}
	/**
	 * 查询项目的返款计划
	 * @return
	 */
	public String listProject(){
		try {
			PFinanceProject pojo = financeProjectQueryService.findFinanceById(id);
			project = financeProjectQueryService.transfer(pojo);
			List<PProjectReturnPlan> pojos = projectReturnPlanQueryService.findProjectReturnPlan(id);
			projectReturnPlans = projectReturnPlanQueryService.transfer(pojos);
		} catch (NotFindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setProjectReturnPlanQueryService(
			ProjectReturnPlanQueryService projectReturnPlanQueryService) {
		this.projectReturnPlanQueryService = projectReturnPlanQueryService;
	}

	public List<DProjectReturnPlan> getProjectReturnPlans() {
		return projectReturnPlans;
	}

	public DFinanceProject getProject() {
		return project;
	}

	public void setFinanceProjectQueryService(
			FinanceProjectQueryService financeProjectQueryService) {
		this.financeProjectQueryService = financeProjectQueryService;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public int getCount() {
		return count;
	}
	
	
	
}
