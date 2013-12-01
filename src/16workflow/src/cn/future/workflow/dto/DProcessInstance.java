package cn.future.workflow.dto;

import java.io.Serializable;

import org.jbpm.pvm.internal.history.model.HistoryProcessInstanceImpl;

import cn.future.util.StringUtil;
import cn.future.util.TimeUtil;

public class DProcessInstance implements Serializable{
	private static final long serialVersionUID = -7905701725341243993L;
	/**
	 * 无餐构造方法
	 */
	public DProcessInstance(){
		
	}
	/**
	 * 使用查询出来的历史流程对象创建
	 * @param d
	 * @param workFlowTitle
	 */
	public DProcessInstance(HistoryProcessInstanceImpl d,String workFlowTitle){
		this.id=d.getProcessInstanceId();
		this.startDate=TimeUtil.dateToYMDHMSString(d.getStartTime());
		this.endDate=TimeUtil.dateToYMDHMSString(d.getEndTime());
		this.workFlowTitle=workFlowTitle;
		this.statusName=d.getEndActivityName();
		this.status=d.getState();
		this.processDefinitionId=d.getProcessDefinitionId();
	}
	private String id;
	private String startDate;
	private String endDate;
	private String workFlowTitle;
	private String statusName;
	private String status;
	private String processDefinitionId;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProcessDefinitionId() {
		return processDefinitionId;
	}
	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getWorkFlowTitle() {
		return workFlowTitle;
	}
	public void setWorkFlowTitle(String workFlowTitle) {
		this.workFlowTitle = workFlowTitle;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
