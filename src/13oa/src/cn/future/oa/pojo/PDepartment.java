package cn.future.oa.pojo;

import java.util.Set;

import cn.future.common.pojo.BasePriority;

public class PDepartment extends BasePriority{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3971741420190376455L;
	private String name;
	/**
	 * 是否是职位，职位下面才能放员工
	 * 顶级 0, 部门 50， 组织100
	 */
	private int level;
	/**
	 * 存放公司信息，不是顶级，就为null
	 */
	private PCompany company; 
	private Set<PDepartment> children;
	private PDepartment parent;
	
	/**
	 * 组织负责人
	 */
	private PAccount principal;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public PCompany getCompany() {
		return company;
	}
	public void setCompany(PCompany company) {
		this.company = company;
	}
	public Set<PDepartment> getChildren() {
		return children;
	}
	public void setChildren(Set<PDepartment> children) {
		this.children = children;
	}
	public PDepartment getParent() {
		return parent;
	}
	public void setParent(PDepartment parent) {
		this.parent = parent;
	}
	
	public PAccount getPrincipal() {
		return principal;
	}
	public void setPrincipal(PAccount principal) {
		this.principal = principal;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
