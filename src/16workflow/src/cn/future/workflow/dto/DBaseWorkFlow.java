package cn.future.workflow.dto;

import java.io.Serializable;

import cn.future.util.StringUtil;
import cn.future.util.TimeUtil;
import cn.future.workflow.pojo.PBaseWorkFlow;

public class DBaseWorkFlow implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 331376347199765447L;
	public DBaseWorkFlow(){
		
	}
	public DBaseWorkFlow(PBaseWorkFlow d){
		this.id=d.getId();
		this.workFlowTitle=d.getWorkFlowTitle();
		this.title=d.getTitle();
		this.content=d.getContent();
		this.comments=d.getComments();
		this.requestId=d.getRequestId();
		this.requestName=d.getRequestName();
		this.submitId=d.getSubmitId();
		this.submitName=d.getSubmitName();
		this.submitTime=TimeUtil.dateToYMDHMSString(d.getSubmitTime());
		this.endTime=TimeUtil.dateToYMDHMSString(d.getEndTime());
		this.needTime=TimeUtil.dateToYMDHMSString(d.getNeedTime());
		this.statusCode=d.getStatusCode();
		this.statusName=d.getStatusName();
		if(this.submitTime==null){
			this.submitTime="无";
		}
		if(this.endTime==null){
			this.endTime="无";
		}
		if(this.needTime==null){
			this.needTime="无";
		}
	}
	public String id;
	public String workFlowTitle;
	
	
	public String title;
	public String content;
	public String comments;
	
	public String requestId; //申请人
	public String requestName;//
	public String submitId;//提交人
	public String submitName;
	
	public String submitTime;
	public String endTime;
	public String needTime; //这个字段用来存放需要在多久内完成任务，但是这个字段暂不启用
	
	public int statusCode;//0未处理，1在处理，200申请通过，400未通过，500流程异常
	public String statusName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWorkFlowTitle() {
		return workFlowTitle;
	}
	public void setWorkFlowTitle(String workFlowTitle) {
		this.workFlowTitle = workFlowTitle;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getRequestName() {
		return requestName;
	}
	public void setRequestName(String requestName) {
		this.requestName = requestName;
	}
	public String getSubmitId() {
		return submitId;
	}
	public void setSubmitId(String submitId) {
		this.submitId = submitId;
	}
	public String getSubmitName() {
		return submitName;
	}
	public void setSubmitName(String submitName) {
		this.submitName = submitName;
	}
	public String getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getNeedTime() {
		return needTime;
	}
	public void setNeedTime(String needTime) {
		this.needTime = needTime;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
