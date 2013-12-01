package cn.future.oa.pojo;

import java.util.Set;

import cn.future.common.pojo.BasePriority;
/**
 * 角色类
 * @author Tony soft-qiyao@foxmail.com
 *
 */
public class Role extends BasePriority{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4446259490381544760L;
	/**
	 * 角色名
	 */
	private String name;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * 角色拥有的系统功能
	 */
	private Set<SysFunction> sysFunctions;
	/**
	 * 角色拥有的系统操作
	 */
	private Set<SysOperation> sysOperations;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<SysFunction> getSysFunctions() {
		return sysFunctions;
	}
	public void setSysFunctions(Set<SysFunction> sysFunctions) {
		this.sysFunctions = sysFunctions;
	}
	public Set<SysOperation> getSysOperations() {
		return sysOperations;
	}
	public void setSysOperations(Set<SysOperation> sysOperations) {
		this.sysOperations = sysOperations;
	}
	
	
}
