package cn.future.notification.dto;

import java.io.Serializable;

import cn.future.notification.pojo.PSysNotification;
import cn.future.util.TimeUtil;

public class DSysNotification  implements Serializable{
	private static final long serialVersionUID = 6221238609551413004L;
	
	public DSysNotification(){
		
	}
	public DSysNotification(PSysNotification d){
		this.id=d.getId();
		this.flowId=d.getFlowId();
		this.title=d.getTitle();
		this.content=d.getContent();
		this.submitName=d.getSubmitName();
		this.accountName=d.getAccountName();
		this.positionName=d.getPositionName();
		this.topInfo=d.getTopInfo();
		this.notificationTypeCode=d.getNotificationTypeCode();
		this.notificationTypeName=d.getNotificationTypeName();
		this.submitDate=TimeUtil.dateToYMDHMSString(d.getSubmitDate());
		this.statusCode=d.getStatusCode();
		this.statusName=d.getStatusName();
	}
	private String id;
	private String flowId;
	private String title;
	private String content;
	private String submitName;
	private String accountName;
	private String positionName;
	private int topInfo; //0 or 1 默认0，如果是1，则置顶
	private String notificationTypeCode;
	private String notificationTypeName;
	private String submitDate;
	private int statusCode;
	private String statusName;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getSubmitName() {
		return submitName;
	}
	public void setSubmitName(String submitName) {
		this.submitName = submitName;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public int getTopInfo() {
		return topInfo;
	}
	public void setTopInfo(int topInfo) {
		this.topInfo = topInfo;
	}
	public String getNotificationTypeCode() {
		return notificationTypeCode;
	}
	public void setNotificationTypeCode(String notificationTypeCode) {
		this.notificationTypeCode = notificationTypeCode;
	}
	public String getNotificationTypeName() {
		return notificationTypeName;
	}
	public void setNotificationTypeName(String notificationTypeName) {
		this.notificationTypeName = notificationTypeName;
	}
	public String getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
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
	public String getFlowId() {
		return flowId;
	}
	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}
	
	

}
