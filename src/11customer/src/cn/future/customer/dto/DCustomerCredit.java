package cn.future.customer.dto;

import java.io.Serializable;

import cn.future.customer.pojo.PCustomerCredit;
import cn.future.util.TimeUtil;

public class DCustomerCredit implements Serializable{

	private static final long serialVersionUID = -4054230222364785199L;

	public DCustomerCredit(){
		
	}
	public DCustomerCredit(PCustomerCredit p ){
		this.id = p.getId();
		this.comments = p.getComments();
		this.updateUser = p.getUpdateUser();
		this.customerId = p.getCustomerId();
		this.content = p.getContent();
		this.addDate = TimeUtil.dateToYMDHMSString(p.getAddDate());
	}
	private String id;
	/**
	 * 抵押备注，内部可见
	 */
	private String comments;
	private String updateUser;
	
	private String customerId;
	/**
	 * HTML编辑字段,但是也不要太大
	 */
	private String content;
	
	private String addDate;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAddDate() {
		return addDate;
	}
	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
}
