package cn.future.customer.action;

import cn.future.common.action.BaseAction;
import cn.future.customer.service.CustomerManageService;
/**
 * 管理客户信息
 */
public class CustomerManageAction extends BaseAction{

	private static final long serialVersionUID = 8761557093416710873L;
	private CustomerManageService customerManageService;
	//set -- get
	
	/**
	 * 添加会员
	 */
	public String add(){
		
		return SUCCESS;
	}

	public String modify(){
		
		return SUCCESS;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setCustomerManageService(CustomerManageService customerManageService) {
		this.customerManageService = customerManageService;
	}
	
	
	
}
