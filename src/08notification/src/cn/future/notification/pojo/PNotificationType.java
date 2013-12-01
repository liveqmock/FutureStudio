package cn.future.notification.pojo;

import cn.future.common.pojo.BasePriority;

public class PNotificationType extends BasePriority{
	private static final long serialVersionUID = 8046940692801603423L;
	private String name;
	private String code; //00 系统通知，10全网通知，20部门通知，其他可以自定义
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
