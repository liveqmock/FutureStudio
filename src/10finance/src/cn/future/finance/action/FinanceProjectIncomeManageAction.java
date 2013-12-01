package cn.future.finance.action;

import cn.future.common.action.BaseAction;
import cn.future.common.exception.NotFindException;
import cn.future.common.exception.SystemBusyException;
import cn.future.finance.exception.FinanceReturnNumberException;
import cn.future.finance.exception.FinanceStatusError;
import cn.future.finance.service.FinanceProjectIncomeManageService;
import cn.future.pay.exception.PayBusinessIdUnuquieException;

public class FinanceProjectIncomeManageAction extends BaseAction {

	private static final long serialVersionUID = -7366691374943805551L;
	private FinanceProjectIncomeManageService financeProjectIncomeManageService;
	//set
	private String id;
	//get set
	
	//get
	private String message;
	/**
	 * 通过计划ID，创建 一组 收入
	 * @return
	 */
	public String addIncomeByPlan(){
		try {
			this.beforeResponse();
			financeProjectIncomeManageService.addIncomeByPlanId(id);
			this.successResponse();
			message = "返款成功";
		} catch (PayBusinessIdUnuquieException e) {
			e.printStackTrace();
			message = e.getMessage();
		} catch (NotFindException e) {
			e.printStackTrace();
			message = e.getMessage();
		} catch (SystemBusyException e) {
			e.printStackTrace();
			message = e.getMessage();
		} catch (FinanceStatusError e) {
			e.printStackTrace();
			message = e.getMessage();
		} catch (FinanceReturnNumberException e) {
			e.printStackTrace();
			message = e.getMessage();
		}
		return SUCCESS;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setFinanceProjectIncomeManageService(
			FinanceProjectIncomeManageService financeProjectIncomeManageService) {
		this.financeProjectIncomeManageService = financeProjectIncomeManageService;
	}

	public String getMessage() {
		return message;
	}

	public void setId(String id) {
		this.id = id;
	}

}
