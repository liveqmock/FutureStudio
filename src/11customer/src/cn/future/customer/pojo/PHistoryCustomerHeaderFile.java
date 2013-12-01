package cn.future.customer.pojo;

import java.io.Serializable;
import java.util.Date;

public class PHistoryCustomerHeaderFile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7494222180596055794L;
	
	private String id;
	private String customerId;
	private String fileId;
	private Date useDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public Date getUseDate() {
		return useDate;
	}
	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
