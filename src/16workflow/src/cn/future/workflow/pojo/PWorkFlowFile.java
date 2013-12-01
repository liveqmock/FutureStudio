package cn.future.workflow.pojo;

import cn.future.common.pojo.BasePriority;

public class PWorkFlowFile extends BasePriority{
	
	/**
	 * 工作流挂文件
	 */
	private static final long serialVersionUID = 5840162184595645438L;
	private String name;
	private String businessId;
	private String fileId;
	private String fileName;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
