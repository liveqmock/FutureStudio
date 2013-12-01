package cn.future.customer.pojo;

import java.io.Serializable;
import java.util.Date;

import cn.future.util.StringUtil;

/**
 * 客户信用
 * 记录客户历史可用于抵押 - 担保的信息
 */
public class PCustomerCredit implements Serializable{

	private static final long serialVersionUID = 7826579875506702048L;

	public PCustomerCredit(){
		
	}
	/**
	 * 新增
	 */
	public PCustomerCredit(String comments, String updateUserId,
			String customerId, String content){
		this.id = StringUtil.getUuid();
		this.comments = comments;
		this.updateUser = updateUserId;
		this.customerId = customerId;
		this.content = content;
		this.addDate = new Date();
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
	
	private Date addDate;
	
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
	public Date getAddDate() {
		return addDate;
	}
	public void setAddDate(Date addDate) {
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
