package cn.future.knowledge.pojo;

import cn.future.common.pojo.BasePriority;
/**
 * 知识条目，附件
 * @author Tony
 *
 */
public class PKnowledgeFile extends BasePriority{
	private static final long serialVersionUID = -9144852205486682541L;
	private String knowledgeId;
	private String fileId;
	
	
	public String getKnowledgeId() {
		return knowledgeId;
	}
	public void setKnowledgeId(String knowledgeId) {
		this.knowledgeId = knowledgeId;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
