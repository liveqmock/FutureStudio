package cn.future.finance.pojo;

import java.io.Serializable;
/**
 * 自主投资项目
 *
 */
public class PExcellenceProject extends PBaseProject implements Serializable{

	private static final long serialVersionUID = -5287009863180704117L;

	private String requestId;
	private String workflowId;//流程，流转id
	

	private String content;
	private String contentComments;
	private String comments;
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getWorkflowId() {
		return workflowId;
	}
	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContentComments() {
		return contentComments;
	}
	public void setContentComments(String contentComments) {
		this.contentComments = contentComments;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	


	

}
