package cn.future.file.pojo;

import java.util.Date;

import cn.future.common.pojo.BasePriority;

public class FileDirectory extends BasePriority{

	private static final long serialVersionUID = 1989948881044204269L;
	private String name; //显示的名字
	private String accountid; //不建立关系
	private Date addTime;//文件添加的时间
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccountid() {
		return accountid;
	}
	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
