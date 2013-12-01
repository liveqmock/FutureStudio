package cn.future.pay.action;

import java.util.ArrayList;
import java.util.List;

import cn.future.common.action.BaseAction;
import cn.future.pay.dto.DCashAccount;
import cn.future.pay.pojo.PCashAccount;
import cn.future.pay.service.CashAccountQueryService;
/**
 * 现金账户查询Action
 */
public class AccountQueryAction extends BaseAction{

	private static final long serialVersionUID = 4826488556601807905L;
	private CashAccountQueryService cashAccountQueryService;
	
	//set get
	private int page=1;
	//get 
	private int pageSize=20;
	private int count;
	private List<DCashAccount> cashAccounts;
	/**
	 * 查询所有的现金账户信息
	 * @return
	 */
	public String list(){
		List<PCashAccount> list = new ArrayList<PCashAccount>();
		cashAccountQueryService.findPCashAccount(list, page, pageSize, null);
		cashAccounts = cashAccountQueryService.transfer(list);
		return SUCCESS;
	}
	public void setCashAccountQueryService(
			CashAccountQueryService cashAccountQueryService) {
		this.cashAccountQueryService = cashAccountQueryService;
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
	public int getPageSize() {
		return pageSize;
	}
	public int getCount() {
		return count;
	}
	public List<DCashAccount> getCashAccounts() {
		return cashAccounts;
	}
	
	
}
