package cn.future.finance.action;

import cn.future.common.action.BaseAction;
import cn.future.common.exception.NotFindException;
import cn.future.finance.exception.FinanceProjectStatusCodeError;
import cn.future.finance.pojo.PFinanceProject;
import cn.future.finance.service.FinanceProjectManageService;
import cn.future.finance.service.FinanceProjectQueryService;

public class FinanceProjectManageAction extends BaseAction{

	private static final long serialVersionUID = -1892454369508521502L;
	//set
	private String id;
	private int statusCode = -1000;
	private PFinanceProject project;
	private FinanceProjectManageService financeProjectManageService;
	private FinanceProjectQueryService financeProjectQueryService;
	private String customerId;
	//get
	private String message;
	/**
	 * 修改项目状态
	 */
	public String updateStatus(){
		try {
			this.beforeResponse();
			PFinanceProject project = financeProjectQueryService.findFinanceById(id);
			financeProjectManageService.updateFinanceProjectStatus(project.getId(), statusCode);
			this.successResponse();
			message="修改成功";
		} catch (NotFindException e) {
			message=e.getMessage();
		} catch (FinanceProjectStatusCodeError e) {
			message=e.getMessage();
		}
		return SUCCESS;
	}
	/**
	 * 添加一个项目
	 * @return
	 */
	public String add(){
		project.init();
		financeProjectManageService.addFinanceProject(project);
		return SUCCESS;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setProject(PFinanceProject project) {
		this.project = project;
	}

	public void setFinanceProjectManageService(
			FinanceProjectManageService financeProjectManageService) {
		this.financeProjectManageService = financeProjectManageService;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getMessage() {
		return message;
	}

	public void setId(String id) {
		this.id = id;
	}
	public void setFinanceProjectQueryService(
			FinanceProjectQueryService financeProjectQueryService) {
		this.financeProjectQueryService = financeProjectQueryService;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

}
