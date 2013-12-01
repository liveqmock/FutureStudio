package cn.future.oa.pojo;

import java.util.HashSet;
import java.util.Set;

import cn.future.common.pojo.BasePriority;

public class SysFunction extends BasePriority{
	public SysFunction(){
		
	}
	public SysFunction(SysFunction s){
		this.id=s.getId();
		this.priority=s.getPriority();
		this.comments=s.getComments();
		
		this.name=s.getName();
		this.funcType=s.getFuncType();
		this.funcValue=s.getFuncValue();
		this.statusCode=s.getStatusCode();
		this.statusName=s.getStatusName();
		this.children=new HashSet<SysFunction>();
	}
	
	private static final long serialVersionUID = -6858504519699218303L;
	private String name;
	private Set<SysFunction> children;//
	private String funcType;  //click,  java?,other, ( Nav  Menu )
	private String funcValue; // ???
	private int statusCode;
	private String statusName;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Set<SysFunction> getChildren() {
		return children;
	}
	public void setChildren(Set<SysFunction> children) {
		this.children = children;
	}
	public String getFuncType() {
		return funcType;
	}
	public void setFuncType(String funcType) {
		this.funcType = funcType;
	}
	public String getFuncValue() {
		return funcValue;
	}
	public void setFuncValue(String funcValue) {
		this.funcValue = funcValue;
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
