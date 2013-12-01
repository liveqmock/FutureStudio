package cn.future.knowledge.pojo;

import java.io.Serializable;
import java.util.Date;

public class PKnowledgeAdvice implements Serializable{

	private static final long serialVersionUID = 1751565732099894743L;
	private String id;
	private Date date;
	private String contentUserName;
	private String content;
	private String knowledgeId;
	private String reply;
	private String replyUserName;
	private String replyUserId;
	
	public String getKnowledgeId() {
		return knowledgeId;
	}
	public void setKnowledgeId(String knowledgeId) {
		this.knowledgeId = knowledgeId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getContentUserName() {
		return contentUserName;
	}
	public void setContentUserName(String contentUserName) {
		this.contentUserName = contentUserName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public String getReplyUserName() {
		return replyUserName;
	}
	public void setReplyUserName(String replyUserName) {
		this.replyUserName = replyUserName;
	}
	public String getReplyUserId() {
		return replyUserId;
	}
	public void setReplyUserId(String replyUserId) {
		this.replyUserId = replyUserId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
