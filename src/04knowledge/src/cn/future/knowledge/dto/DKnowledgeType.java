package cn.future.knowledge.dto;

import java.util.List;

import cn.future.common.pojo.BasePriority;
import cn.future.knowledge.pojo.PKnowledgeType;

public class DKnowledgeType extends BasePriority{
	public DKnowledgeType(){
		
	}
	public DKnowledgeType(PKnowledgeType d){
		super(d);
		this.name=d.getName();
		this.flowId=d.getFlowId();
		this.parentId=d.getParentId();
		this.parentName=d.getParentName();
		this.isDirectory=d.getIsDirectory();
		this.outReader=d.getOutReader();
		this.statusCode=d.getStatusCode();
		this.statusName=d.getStatusName();
	}
	private static final long serialVersionUID = -5496428810067003562L;
	private String name;	
	private String flowId;//流水号
	private String parentId;//要么是0，要么是具体的id, 父ID只能是目录
	private String parentName;//要么是空数组，要么有具体的名字
	private int isDirectory;//是否是目录， 1是目录，0不是目录
	private int outReader;//外部用户是否可以访问 --顶级目录不设置
	private int statusCode;
	private String statusName;
	
	private List<DKnowledgeType> children;
	
	
	public List<DKnowledgeType> getChildren() {
		return children;
	}
	public void setChildren(List<DKnowledgeType> children) {
		this.children = children;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFlowId() {
		return flowId;
	}
	public void setFlowId(String flowId) {
		this.flowId = flowId;
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
	public int getIsDirectory() {
		return isDirectory;
	}
	public void setIsDirectory(int isDirectory) {
		this.isDirectory = isDirectory;
	}
	public int getOutReader() {
		return outReader;
	}
	public void setOutReader(int outReader) {
		this.outReader = outReader;
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
