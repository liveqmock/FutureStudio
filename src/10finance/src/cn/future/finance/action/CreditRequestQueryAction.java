package cn.future.finance.action;

import java.util.ArrayList;
import java.util.List;

import cn.future.common.action.BaseAction;
import cn.future.finance.dto.DCreditRequest;
import cn.future.finance.pojo.PCreditRequest;
import cn.future.finance.service.CreditRequestQueryService;

public class CreditRequestQueryAction extends BaseAction{

	private static final long serialVersionUID = 8464282445497748647L;
	//set
	private String query;
	private CreditRequestQueryService creditRequestQueryService;
	private int page = 1;
	//set get 
	private int pageSize = 20;
	//get
	private String message;
	private List<DCreditRequest> creditRequests;
	private int count;
	
	/**
	 * 查询借款申请
	 * @return
	 */
	public String list(){
		List<PCreditRequest> list = new ArrayList<PCreditRequest>();
		count = creditRequestQueryService.findCreditRequest(list, null, null, page, pageSize);
		creditRequests = creditRequestQueryService.transfer(list);
		return SUCCESS;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getMessage() {
		return message;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public void setCreditRequestQueryService(
			CreditRequestQueryService creditRequestQueryService) {
		this.creditRequestQueryService = creditRequestQueryService;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public List<DCreditRequest> getCreditRequests() {
		return creditRequests;
	}

	public int getCount() {
		return count;
	}
	
	
}
