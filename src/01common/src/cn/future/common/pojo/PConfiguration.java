package cn.future.common.pojo;

import cn.future.common.pojo.BasePriority;
import cn.future.util.StringUtil;

public class PConfiguration extends BasePriority{
	/**
	 * 
	 */
	private static final long serialVersionUID = 130022635392897000L;
	private String value;
	private String name;
	private String hostid;//属主id，可能是USER，也可能是部门
	
	public PConfiguration(){
		
	}
	public PConfiguration(String comments, int priority, String name,String value, String hostId){
		this.id = StringUtil.getUuid();
		this.priority = priority;
		this.comments = comments;
		this.name = name;
		this.value = value;
		this.hostid = hostId;
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
