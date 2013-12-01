package cn.future.knowledge.pojo;

import cn.future.common.pojo.BasePriority;
/**
 * 知识条目分类，只能有两级
 * @author Tony
 *
 */
public class PKnowledgeType extends BasePriority{
	private static final long serialVersionUID = -7618794003099973350L;
	
	private String name;	
	private String flowId;//流水号
	private String parentId;//要么是0，要么是具体的id, 父ID只能是目录
	private String parentName;//要么是空数组，要么有具体的名字
	private int isDirectory;//是否是目录， 1是目录，0不是目录
	private int outReader;//外部用户是否可以访问
	private int statusCode;
	private String statusName;
	
	
	public int getIsDirectory() {
		return isDirectory;
	}
	public void setIsDirectory(int isDirectory) {
		this.isDirectory = isDirectory;
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
	public String getFlowId() {
		return flowId;
	}
	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public int getOutReader() {
		return outReader;
	}
	public void setOutReader(int outReader) {
		this.outReader = outReader;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
