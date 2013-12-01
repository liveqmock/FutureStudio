package cn.future.finance.pojo;

import java.io.Serializable;
/**
 * 项目使用的信用信息, 中间关联类
 */
public class PFinanceProjectCredit implements Serializable{
	private static final long serialVersionUID = 8004886412507643474L;
	private String id;
	private String projectId;
	private String creditId;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getCreditId() {
		return creditId;
	}
	public void setCreditId(String creditId) {
		this.creditId = creditId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
