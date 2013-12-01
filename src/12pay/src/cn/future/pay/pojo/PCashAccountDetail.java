package cn.future.pay.pojo;

import java.io.Serializable;
import java.util.Date;

import cn.future.util.StringUtil;

public class PCashAccountDetail  implements Serializable{

	private static final long serialVersionUID = 5733625474315450416L;
	public PCashAccountDetail(){
		
	}
	public PCashAccountDetail(String cashAccountId, String businessId,
			long cashNumber, long oldNumber, long newNumber, String operate, String title, String content,
			String comments, String tagCode) {
		super();
		this.id = StringUtil.getUuid();
		this.updateDate = new Date();
		this.cashAccountId = cashAccountId;
		this.businessId = businessId;
		this.oldNumber = oldNumber;
		this.cashNumber = cashNumber;
		this.newNumber = newNumber;
		this.cashOperate = operate;
		this.title = title;
		this.content = content;
		this.comments = comments;
		this.tagCode = tagCode;
	}
	//需要事务
	private String id;  //系统ID -- 记账系统id
	private int version;
	private String businessId; //唯一键
	// 进行入账的id, 不同的id有不同的生成策略
	//长度暂定是256 不能超过256位
	//各自使用不同的业务ID策略进行唯一校验
	private String cashAccountId;
	
	private long oldNumber;
	private long cashNumber; //都是正数  单位分
	private long newNumber;
	private String cashOperate; // -   +
	private Date updateDate;
	private String title;
	private String content;
	private String comments;
	private String tagCode="";

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getCashAccountId() {
		return cashAccountId;
	}
	public void setCashAccountId(String cashAccountId) {
		this.cashAccountId = cashAccountId;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	
	public long getOldNumber() {
		return oldNumber;
	}
	public void setOldNumber(long oldNumber) {
		this.oldNumber = oldNumber;
	}
	public long getCashNumber() {
		return cashNumber;
	}
	public void setCashNumber(long cashNumber) {
		this.cashNumber = cashNumber;
	}
	public long getNewNumber() {
		return newNumber;
	}
	public void setNewNumber(long newNumber) {
		this.newNumber = newNumber;
	}
	public String getCashOperate() {
		return cashOperate;
	}
	public void setCashOperate(String cashOperate) {
		this.cashOperate = cashOperate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
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
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getTagCode() {
		return tagCode;
	}
	public void setTagCode(String tagCode) {
		this.tagCode = tagCode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
