package cn.future.oa.dto;

import java.util.ArrayList;
import java.util.List;

import cn.future.common.pojo.BasePriority;
import cn.future.oa.pojo.SysFunction;

public class DSysFunction extends BasePriority {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5044423109983321135L;
	
	public DSysFunction(SysFunction d,boolean fetchChildren){
		this.id=d.getId();
		this.priority=d.getPriority();
		this.comments=d.getComments();
		
		this.name=d.getName();
		this.funcType=d.getFuncType();
		this.funcValue=d.getFuncValue();
		this.statusCode=d.getStatusCode();
		this.statusName=d.getStatusName();
		this.children=new ArrayList<DSysFunction>();
		if(fetchChildren && d.getChildren()!=null){
			for(SysFunction s: d.getChildren()){
				this.children.add(new DSysFunction(s,true));
			}
		}
	}
	
	private String name;
	private List<DSysFunction> children;//
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

	public List<DSysFunction> getChildren() {
		return children;
	}
	public void setChildren(List<DSysFunction> children) {
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
