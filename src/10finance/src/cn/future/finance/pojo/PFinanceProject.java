package cn.future.finance.pojo;

import java.util.Date;

import cn.future.customer.dto.DCustomer;
import cn.future.oa.dto.DAccount;

/**
 * 投资理财项目，基础对象
 * 投资项目  -- 抵押投资 - 担保投资
 */
public class PFinanceProject extends PBaseProject {

	private static final long serialVersionUID = -4105077159670635625L;
	/**
	 * 借款用户
	 * 用户提交的集资项目
	 */
	private String customerId; 
	/**
	 * 流程，流转id, 工作流使用，暂时使用0
	 */
	private String workflowId = "0";
	public PFinanceProject(){
		
	}
	public PFinanceProject(DCustomer cus,DAccount account, String timeType, String title, Long financeNumber,
			String content, String contentComments, String comments, String yearRate, int projectMonth,
			int returnTypeCode, String returnInfo, int projectTypeCode, long minNumber, long stepNumber,
			long maxNumber, Date startDate, Date deadDate, int statusCode){
		
		super(account,timeType, title, financeNumber,
				content, contentComments, comments, yearRate, projectMonth,
				returnTypeCode, returnInfo, projectTypeCode, minNumber, stepNumber,
				maxNumber, startDate, deadDate, statusCode);
		
		this.customerId = cus.getId();
		
	}
	public void init(){
		this.initBase();
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
	

	
	
	
}
