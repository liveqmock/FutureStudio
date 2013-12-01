package cn.future.finance.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.future.common.action.BaseAction;
import cn.future.finance.dto.DFinanceProjectIncome;
import cn.future.finance.pojo.PFinanceProjectIncome;
import cn.future.finance.service.FinanceProjectIncomeQueryService;

public class FinanceProjectIncomeQueryAction extends BaseAction {

	private static final long serialVersionUID = -3557251342001176691L;
	private FinanceProjectIncomeQueryService financeProjectIncomeQueryService;
	//set
	private int typeCode=2; ////收入类型   1本金 2利息
	//set get
	private int page=1;
	//get
	private int count;
	private int pageSize=20;
	private List<DFinanceProjectIncome> incomes;
	/**
	 * 列出详细的收入信息
	 * @return
	 */
	public String list(){
		List<PFinanceProjectIncome> list = new ArrayList<PFinanceProjectIncome>();
		String cusId = null;
		String projectId = null;
		String bidId = null;
		Date start = null;
		Date end = null;
		count = financeProjectIncomeQueryService.findFinanceProjectIncome(list, cusId, projectId, bidId, 
				new Integer(typeCode), start, end,
				new Integer(page), new Integer(pageSize));
		incomes = financeProjectIncomeQueryService.transfer(list);
		return SUCCESS;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getCount() {
		return count;
	}
	public int getPageSize() {
		return pageSize;
	}
	public List<DFinanceProjectIncome> getIncomes() {
		return incomes;
	}
	public void setFinanceProjectIncomeQueryService(
			FinanceProjectIncomeQueryService financeProjectIncomeQueryService) {
		this.financeProjectIncomeQueryService = financeProjectIncomeQueryService;
	}
	public void setTypeCode(int typeCode) {
		this.typeCode = typeCode;
	}
	
	
}
