package cn.future.oa.pojo;

import cn.future.common.pojo.BasePriority;

public class SysOperation extends BasePriority{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6831829852441662925L;
	private String name;
	private int statusCode;
	private String statusName;
	
	private String operationTypeCode;
	private String operationTypeName;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getOperationTypeCode() {
		return operationTypeCode;
	}
	public void setOperationTypeCode(String operationTypeCode) {
		this.operationTypeCode = operationTypeCode;
	}
	public String getOperationTypeName() {
		return operationTypeName;
	}
	public void setOperationTypeName(String operationTypeName) {
		this.operationTypeName = operationTypeName;
	}
	
	
}
