package cn.future.oa.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.future.common.pojo.BasePriority;
import cn.future.oa.pojo.Role;
import cn.future.oa.pojo.SysFunction;
import cn.future.oa.pojo.SysOperation;

public class DRole extends BasePriority {
	
	public DRole(){
		
	}
	public DRole(Role d,boolean fetchPrivilege){
		this.id=d.getId();
		this.priority=d.getPriority();
		this.comments=d.getComments();
		
		this.name=d.getName();
		
		sysFunctions = new ArrayList<SysFunction>();
		sysOperations = new ArrayList<SysOperation>();
		if(fetchPrivilege){
			Set<SysFunction> setFunc=d.getSysFunctions();
			Set<SysOperation> setOper = d.getSysOperations();
			for(SysFunction s:setFunc){
				sysFunctions.add(s);
			}
			
			for(SysOperation s:setOper){
				sysOperations.add(s);
			}
		}
		
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -6057268001849885415L;
	private String name;
	private List<SysFunction> sysFunctions;
	private List<SysOperation> sysOperations;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<SysFunction> getSysFunctions() {
		return sysFunctions;
	}
	public void setSysFunctions(List<SysFunction> sysFunctions) {
		this.sysFunctions = sysFunctions;
	}
	public List<SysOperation> getSysOperations() {
		return sysOperations;
	}
	public void setSysOperations(List<SysOperation> sysOperations) {
		this.sysOperations = sysOperations;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	} 
	
	
}
