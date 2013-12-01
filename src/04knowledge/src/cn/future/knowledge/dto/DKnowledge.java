package cn.future.knowledge.dto;

import java.io.Serializable;
import java.util.Calendar;

import cn.future.knowledge.pojo.PKnowledge;

public class DKnowledge implements Serializable{
	private static final long serialVersionUID = 8337657285886741225L;
	public DKnowledge(){
		
	}
	public DKnowledge(PKnowledge d){
		this.id=d.getId();
		this.flowId=d.getFlowId();
		this.title=d.getTitle();
		Calendar c = Calendar.getInstance();
		c.setTime(d.getDate());
		this.date=c.get(Calendar.YEAR)+"-"+(c.get(Calendar.MONTH)+1)+"-"+c.get(Calendar.DAY_OF_MONTH);
		this.content=d.getContent();
		this.comments=d.getComments();
		this.keyWords=d.getKeyWords();
		this.knowledgeTypeId=d.getKnowledgeTypeId();
		this.knowledgeTypeName=d.getKnowledgeTypeName();
		this.userId=d.getUserId();
		this.userName=d.getUserName();
		this.version=d.getVersion();
		this.readTime=d.getReadTime();
		this.relatedId=d.getRelatedId();
		this.statusCode=d.getStatusCode();
		this.statusName=d.getStatusName();
	}
	
	private String id;
	private String flowId;//流水id
	private String title;
	private String date;
	private String content;
	private String comments;
	private String keyWords;//采用逗号分割
	private String knowledgeTypeId;
	private String knowledgeTypeName;
	private String userId;
	private String userName;
	private int version;
	private int readTime;//初始是0
	private String relatedId;
	private String relatedName;
	private int statusCode;
	private String statusName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFlowId() {
		return flowId;
	}
	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
	public String getKeyWords() {
		return keyWords;
	}
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}
	public String getKnowledgeTypeId() {
		return knowledgeTypeId;
	}
	public void setKnowledgeTypeId(String knowledgeTypeId) {
		this.knowledgeTypeId = knowledgeTypeId;
	}
	public String getKnowledgeTypeName() {
		return knowledgeTypeName;
	}
	public void setKnowledgeTypeName(String knowledgeTypeName) {
		this.knowledgeTypeName = knowledgeTypeName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public int getReadTime() {
		return readTime;
	}
	public void setReadTime(int readTime) {
		this.readTime = readTime;
	}
	public String getRelatedId() {
		return relatedId;
	}
	public void setRelatedId(String relatedId) {
		this.relatedId = relatedId;
	}
	public String getRelatedName() {
		return relatedName;
	}
	public void setRelatedName(String relatedName) {
		this.relatedName = relatedName;
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
