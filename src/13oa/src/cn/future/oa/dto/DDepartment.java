package cn.future.oa.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.future.common.pojo.BasePriority;
import cn.future.oa.pojo.PDepartment;

public class DDepartment extends BasePriority{
	public DDepartment(PDepartment d,boolean fetchChildren){
		if(fetchChildren){
			//TODO
			this.children=new ArrayList<DDepartment>();
			Set<PDepartment> setC=d.getChildren();
			for(PDepartment i:setC){
				this.children.add(new DDepartment(i,true));
			}
		}
		this.id=d.getId();
		this.priority=d.getPriority();
		this.comments=d.getComments();
		this.name=d.getName();
		this.dlevel=d.getLevel();
		if(this.dlevel==100){
			this.isParent=false;
		}
		this.principal=new DAccount(d.getPrincipal());
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -328835927178628771L;
	private String name;
	/**
	 * 是否是职位，职位下面才能放员工
	 * 顶级 0, 部门 50， 组织100
	 */
	private int dlevel;
	/**
	 * 存放公司信息，不是顶级，就为null
	 */
	private boolean isParent=true;
	private List<DDepartment> children;
	/**
	 * 组织负责人
	 */
	private DAccount principal;
	private boolean nocheck=true;
	/**
	 * 是否只有叶子结点可以选取
	 * @param Rule 0只有叶子结点可以选取，1所有结点可以选取，-1所有的都不可以选取
	 */
	public void checkRule(int Rule){
		if(Rule==0){
			if(this.dlevel==100){
				this.nocheck=false;
			}else{
				this.nocheck=true;
			}			
		}else if(Rule==1){
			this.nocheck=false;
		}else if(Rule==-1){
			this.nocheck=true;
		}
		if(this.children!=null){
			for(DDepartment d:this.children){
				d.checkRule(Rule);
			}
		}
	}
	public boolean isNocheck() {
		return nocheck;
	}
	public void setNocheck(boolean nocheck) {
		this.nocheck = nocheck;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public int getDlevel() {
		return dlevel;
	}
	public void setDlevel(int dlevel) {
		this.dlevel = dlevel;
	}
	public DAccount getPrincipal() {
		return principal;
	}
	public void setPrincipal(DAccount principal) {
		this.principal = principal;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<DDepartment> getChildren() {
		return children;
	}
	public void setChildren(List<DDepartment> children) {
		this.children = children;
	}
	public boolean isParent() {
		return isParent;
	}
	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}
	
	
}
