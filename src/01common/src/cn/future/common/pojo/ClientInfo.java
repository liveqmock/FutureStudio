package cn.future.common.pojo;

import java.io.Serializable;
import java.util.Date;
/**
 * 访客信息
 */
public class ClientInfo implements Serializable {
	private static final long serialVersionUID = -4510239539062828489L;
	private String id;
	private String addressIp;
	private String remoteBrowser;
	private Date visitDate;
	
	public Date getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAddressIp() {
		return addressIp;
	}
	public void setAddressIp(String addressIp) {
		this.addressIp = addressIp;
	}
	public String getRemoteBrowser() {
		return remoteBrowser;
	}
	public void setRemoteBrowser(String remoteBrowser) {
		this.remoteBrowser = remoteBrowser;
	}
	
	
}
