package cn.future.notification.pojo;

import java.io.Serializable;
import java.util.Date;
/**
 * 系统通知类
 * @author Tony soft-qiyao@foxmail.com
 *
 */
public class PSysNotification implements Serializable{

	private static final long serialVersionUID = 7863785579391321265L;
	public static final int Unread=1;
	public static final int Readed=0;
	public static final String UnreadString="未读";
	public static final String ReadedString="已读";
	
	private String id;
	private String flowId;
	private String title;
	private String content;
	private String submitName;
	private String submitId;
	private String accountId;
	private String accountName;
	private String positionId;
	private String positionName;
	private int topInfo; //0 or 1 默认0，如果是1，则置顶
	private String notificationTypeCode;
	private String notificationTypeName;
	private Date submitDate;
	private int statusCode;  //1未读， 0已阅
	private String statusName;  //
	
	
	public String getFlowId() {
		return flowId;
	}
	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}
	public String getSubmitName() {
		return submitName;
	}
	public void setSubmitName(String submitName) {
		this.submitName = submitName;
	}
	
	public String getSubmitId() {
		return submitId;
	}
	public void setSubmitId(String submitId) {
		this.submitId = submitId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getPositionId() {
		return positionId;
	}
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

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
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
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
	public Date getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(Date submitDate) {
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

	
}
