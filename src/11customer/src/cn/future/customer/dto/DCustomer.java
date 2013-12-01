package cn.future.customer.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.future.customer.pojo.PCustomer;
import cn.future.oa.dto.DAccount;
import cn.future.util.TimeUtil;

public class DCustomer implements Serializable{

	private static final long serialVersionUID = 2018169873349014114L;

	public DCustomer(){
		
	}
	public DCustomer(PCustomer p){
		this.id=p.getId();
		this.accountName=p.getAccountName();
		this.nickName = p.getNickName();
		this.cnName=p.getCnName();
		this.pinyin=p.getPinyin();
		this.email=p.getEmail();
		this.mobilePhone=p.getMobilePhone();
		this.statusCode=p.getStatusCode();
		this.statusName=p.getStatusName();
		this.createTime=TimeUtil.dateToYMDString(p.getCreateTime());
		this.adviserEmployId = p.getAdviserEmployId();
		
		this.idCard=p.getIdCard();
		this.idCardPicId=p.getIdCardPicId();
		this.headerFile=p.getHeaderFile();
		
	}
	private String id;
	private String accountName;
	private String nickName;
	private String cnName;
	private String pinyin;
	private String email;
	private String mobilePhone;
	private int statusCode;
	private String statusName;
	private String createTime;
	private String adviserEmployId;
	
	private String idCard;//身份证
	private String idCardPicId;
	private String headerFile;//可以为“”，空  -- 客户头像
	
	private List<DCustomerCredit> credits = new ArrayList<DCustomerCredit>();
	private DCustomerDetailInfo detailInfo;
	private DAccount adviserEmploy;
	private Date signInTime;//并不用于数据存储，而用于管理当前客户登陆状态，session模块使用
	
	
 	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Date getSignInTime() {
		return signInTime;
	}
	public void setSignInTime(Date signInTime) {
		this.signInTime = signInTime;
	}
	public DAccount getAdviserEmploy() {
		return adviserEmploy;
	}
	public void setAdviserEmploy(DAccount adviserEmploy) {
		this.adviserEmploy = adviserEmploy;
	}
	public String getAdviserEmployId() {
		return adviserEmployId;
	}
	public void setAdviserEmployId(String adviserEmployId) {
		this.adviserEmployId = adviserEmployId;
	}
	public DCustomerDetailInfo getDetailInfo() {
		return detailInfo;
	}
	public void setDetailInfo(DCustomerDetailInfo detailInfo) {
		this.detailInfo = detailInfo;
	}
	public List<DCustomerCredit> getCredits() {
		return credits;
	}
	public void setCredits(List<DCustomerCredit> credits) {
		this.credits = credits;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getIdCardPicId() {
		return idCardPicId;
	}

	public void setIdCardPicId(String idCardPicId) {
		this.idCardPicId = idCardPicId;
	}

	public String getHeaderFile() {
		return headerFile;
	}

	public void setHeaderFile(String headerFile) {
		this.headerFile = headerFile;
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

	private String customerLevelId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getCustomerLevelId() {
		return customerLevelId;
	}

	public void setCustomerLevelId(String customerLevelId) {
		this.customerLevelId = customerLevelId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
