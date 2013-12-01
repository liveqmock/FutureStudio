package cn.future.security.pojo;

import java.util.Date;

import cn.future.util.StringUtil;

public class PSecurityCode {
	public PSecurityCode() {

	}

	public PSecurityCode(String userId, String businessId, String businessTag, int availableMinutes) {
		this.id = StringUtil.getUuid();
		this.code = String.valueOf((int) Math.random() * 100000);
		if(userId!=null){
			this.userId=userId;
		}else{
			this.userId="";
		}
		if(null!=businessId){
			this.businessId = businessId;
		}else{
			this.businessId="";
		}
		if(null!=businessTag){
			this.businessTag = businessTag;
		}else{
			this.businessTag = "";
		}
		Date nowTime = new Date();
		Date deadTime = new Date();
		this.createDate = nowTime;
		deadTime.setTime(nowTime.getTime() + availableMinutes * 60 * 1000);
		this.deadDate = deadTime;
		this.available = 1;
	}

	private String id;
	private String code;
	
	private String userId;
	private String businessId;
	private String businessTag;
	
	private Date createDate;
	private Date deadDate;
	private int available;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getDeadDate() {
		return deadDate;
	}

	public void setDeadDate(Date deadDate) {
		this.deadDate = deadDate;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getBusinessTag() {
		return businessTag;
	}

	public void setBusinessTag(String businessTag) {
		this.businessTag = businessTag;
	}

}
