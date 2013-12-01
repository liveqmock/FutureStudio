package cn.future.finance.dto;

import java.util.List;

import cn.future.customer.dto.DCustomer;
import cn.future.customer.dto.DCustomerCredit;
import cn.future.finance.pojo.PFinanceProject;

public class DFinanceProject extends DBaseProject {
	private static final long serialVersionUID = -4857954092634891138L;
	public DFinanceProject(){
		super();
	}
	public DFinanceProject(PFinanceProject p){
		super(p);
	}
	private DCustomer customer;
	/**
	 * 用户本次使用的信用
	 */
	private List<DCustomerCredit> credits;
	/**
	 * 借款用户
	 * 用户提交的集资项目
	 */
	private String customerId; 
	/**
	 * 流程，流转id, 工作流使用，暂时使用0
	 */
	private String workflowId = "0";
	
	
	public DCustomer getCustomer() {
		return customer;
	}
	public void setCustomer(DCustomer customer) {
		this.customer = customer;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getWorkflowId() {
		return workflowId;
	}
	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<DCustomerCredit> getCredits() {
		return credits;
	}
	public void setCredits(List<DCustomerCredit> credits) {
		this.credits = credits;
	}
	
	
	
}
