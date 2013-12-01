package cn.future.workflow.business.activity;

import java.io.Serializable;

public class JavaTaskUpdateStatus implements Serializable{
	/**
	 * 
	 */
	private String businessType;
	private static final long serialVersionUID = -2759566538674258243L;
	public JavaTaskUpdateStatus(){
		
	}
	public void updateBusinessStatus(String status,String id){
		String hql = "update "+businessType+" as w set w.status='"+status+"' where w.id='"+id+"'";
	}
	/**
	 * type需要是全路径
	 * @param businessType
	 */
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	
	
}
