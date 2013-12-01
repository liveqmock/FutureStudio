package cn.future.workflow.pojo;

import java.io.Serializable;
import java.util.Date;

public class PBaseWorkFlow implements Serializable{
	public static int untreated=0;
	public static int processing=1;
	public static int proval=200;
	public static int cancel=400;
	public static int error=500;
	
	public static String untreatedString="未处理";
	public static String processingString="正在处理";
	public static String provalString="已结束";
	public static String cancelString="未通过";
	public static String errorString="办理出错";
	
	public PBaseWorkFlow(PBaseWorkFlow d){
		this.id=d.getId();
		this.content=d.getContent();
		this.comments=d.getComments();
		this.requestId=d.getRequestId();
		this.requestName=d.getRequestName();
		this.submitId=d.getSubmitId();
		this.submitName=d.getSubmitName();
		this.submitTime=d.getSubmitTime();
		this.endTime=d.getEndTime();
		this.needTime=d.needTime;
	}
	public PBaseWorkFlow(){
		
	}
	/**
	 * 工作流业务域对象，基础对象
	 */
	private static final long serialVersionUID = 3350865647274336104L;

	public String id;
	public String workFlowTitle;
	
	
	public String title;
	public String content;
	public String comments;
	
	public String requestId; //申请人
	public String requestName;//
	public String submitId;//提交人
	public String submitName;
	
	public Date submitTime;
	public Date endTime;
	public Date needTime; //这个字段用来存放需要在多久内完成任务，但是这个字段暂不启用
	
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
	
	public Date getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getNeedTime() {
		return needTime;
	}
	public void setNeedTime(Date needTime) {
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
