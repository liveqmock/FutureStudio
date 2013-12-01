package cn.future.mail.pojo;

import java.io.Serializable;
import java.util.Date;

public class PMail implements Serializable{
	public static int unsend=-1;
	public static String unsendString="未发送<br/>邮件功能不可用";
	public static String blockString="未发送<br/>目标地址拒绝";
	public static int sendFailed=0;
	public static String sendFailedString="发送失败";
	public static int sent=1;
	public static String sentString="已发送";
	
	private static final long serialVersionUID = 6933894568586286385L;
	private String id;
	private String module;
	private String title;
	private String content;
	private String targetAddress;
	private String sendName;
	private Date sendDate;
	private int statusCode;
	private String statusName;
	
	public PMail(){
		
	}
	public PMail(String title, String content, String targetAddress){
		this.title = title;
		this.content = content;
		this.targetAddress = targetAddress;
	}
	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getId() {
		return id;
	}
	
	public String getSendName() {
		return sendName;
	}

	public void setSendName(String sendName) {
		this.sendName = sendName;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTargetAddress() {
		return targetAddress;
	}
	public void setTargetAddress(String targetAddress) {
		this.targetAddress = targetAddress;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
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
	
	
}
