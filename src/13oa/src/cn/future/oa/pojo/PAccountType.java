package cn.future.oa.pojo;

import cn.future.common.pojo.BasePriority;

public class PAccountType extends BasePriority{

	/**
	 * 员工在职性质
	 */
	private static final long serialVersionUID = -5395953292630550162L;
	private String name;
	
	
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
