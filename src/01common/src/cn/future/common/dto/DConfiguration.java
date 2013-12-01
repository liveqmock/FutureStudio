package cn.future.common.dto;

import cn.future.common.pojo.BasePriority;
import cn.future.common.pojo.PConfiguration;

public class DConfiguration extends BasePriority{
	/**
	 * 
	 */
	private static final long serialVersionUID = 130022635392897000L;
	private String value;
	private String name;
	private String hostid;//属主id，可能是USER，也可能是部门

	public DConfiguration(){
		
	}
	public DConfiguration(PConfiguration p){
		super(p);
		this.value = p.getValue();
		this.name = p.getName();
		this.hostid = p.getHostid();
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHostid() {
		return hostid;
	}
	public void setHostid(String hostid) {
		this.hostid = hostid;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
