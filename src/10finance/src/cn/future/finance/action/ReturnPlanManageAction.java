package cn.future.finance.action;

import java.util.Date;

import cn.future.common.action.BaseAction;
import cn.future.common.exception.NotFindException;
import cn.future.finance.exception.FinanceStatusError;
import cn.future.finance.pojo.PFinanceProject;
import cn.future.finance.pojo.PProjectReturnPlan;
import cn.future.finance.service.FinanceProjectQueryService;
import cn.future.finance.service.ProjectReturnPlanManageService;

public class ReturnPlanManageAction extends BaseAction{

	private static final long serialVersionUID = 7437928946647884393L;

	//set
	private String id;
	
	private int currentMonth;
	private Date returnDate;
	private int statusCode;
	private double percentNumber;
	private String percentShow;
	private	String comments;
	private int typeCode=2;//1本金 2利息
	private ProjectReturnPlanManageService projectReturnPlanManageService;
	private FinanceProjectQueryService financeProjectQueryService;
	//get
	private String message;

	/**
	 * 添加还款计划
	 * @return
	 */
	public String add(){
		try {
			PFinanceProject project = financeProjectQueryService.findFinanceById(id);

			PProjectReturnPlan pojo = new PProjectReturnPlan(project,currentMonth,returnDate,statusCode,typeCode,percentNumber,percentShow,comments);
			projectReturnPlanManageService.addReturnPlan(pojo);
			message = "添加成功";
		} catch (NotFindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 删除指定ID的还款计划
	 * @return
	 */
	public String delete(){
		response.setStatus(500);
		try {
			projectReturnPlanManageService.deleteReturnPlan(id);
			response.setStatus(200);
		} catch (FinanceStatusError e) {
			message = e.getMessage();
		} catch (NotFindException e) {
			message = "该数据不存在，请刷新后重试";
		}
		return SUCCESS;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setProjectReturnPlanManageService(
			ProjectReturnPlanManageService projectReturnPlanManageService) {
		this.projectReturnPlanManageService = projectReturnPlanManageService;
	}
	public void setCurrentMonth(int currentMonth) {
		this.currentMonth = currentMonth;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public void setPercentNumber(double percentNumber) {
		this.percentNumber = percentNumber;
	}
	public void setPercentShow(String percentShow) {
		this.percentShow = percentShow;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public void setFinanceProjectQueryService(
			FinanceProjectQueryService financeProjectQueryService) {
		this.financeProjectQueryService = financeProjectQueryService;
	}

	
}
