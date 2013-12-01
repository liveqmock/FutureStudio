package cn.future.customer.dto;

import cn.future.common.pojo.BasePriority;

public class DCustomerLevel extends BasePriority{

	private static final long serialVersionUID = -6749781640825286812L;
	
	private int level;
	private String name;
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
