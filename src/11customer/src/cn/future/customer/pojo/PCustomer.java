package cn.future.customer.pojo;

import java.io.Serializable;
import java.util.Date;

import cn.future.util.StringUtil;

public class PCustomer implements Serializable{

	private static final long serialVersionUID = 2018169873349014114L;

	private String id;
	private String accountName;   //最长 64字符
	private String accountPassword;   //最长 64字符
	private String moneyPassword; //支付 - 提现密码
	private String nickName;//昵称, 外部用户只能查看这个名称
	private String cnName;
	private String pinyin;
	private String email;
	private String mobilePhone;
	private int statusCode;
	private String statusName;
	private Date createTime;
	private String adviserEmployId;
	
	private String idCard;//身份证
	private String idCardPicId;
	private String headerFile;//可以为“”，空  -- 客户头像

	public PCustomer(PCustomerPreRegister p){
		this.id = p.getId();
		this.accountName = p.getAccountName();
		this.accountPassword = StringUtil.sha512Encrypt(p.getPassword());
		this.moneyPassword = this.accountPassword;
		this.cnName = p.getCnName();
		this.pinyin = StringUtil.getPinyinString(this.cnName);
		this.email = p.getAccountName();
		this.mobilePhone = p.getMobilePhone();
		this.adviserEmployId = p.getAdviserEmployId();
		this.initExtDetail();
		this.createTime = p.getAddDate();
	}
	public void initExtDetail(){
		this.statusCode = 1;
		this.statusName = "可用";
		this.createTime = new Date();
		this.idCard = "";
		this.idCardPicId = "";
		this.headerFile = "";
	}
	public PCustomer(String bindType){
		this.id = StringUtil.getUuid();
		this.accountName = bindType+"_"+this.id;
		this.accountPassword = "";
		this.moneyPassword = "";
		this.cnName="";
		this.pinyin="";
		this.email="";
		this.mobilePhone="";
		this.initExtDetail();
	}
	public PCustomer(String accountName, String pasword, String cnName, String email, String mobilePhone){
		this.id = StringUtil.getUuid();
		this.accountName = accountName;
		this.accountPassword = StringUtil.sha512Encrypt(pasword);
		this.moneyPassword = this.accountPassword;
		this.cnName = cnName;
		this.pinyin = StringUtil.getPinyinString(cnName);
		this.email = email;
		this.mobilePhone = mobilePhone;
		this.initExtDetail();
	}
	public PCustomer(){
		
	}
	
	public String getAdviserEmployId() {
		return adviserEmployId;
	}
	public void setAdviserEmployId(String adviserEmployId) {
		this.adviserEmployId = adviserEmployId;
	}
	public String getMoneyPassword() {
		return moneyPassword;
	}

	public void setMoneyPassword(String moneyPassword) {
		this.moneyPassword = moneyPassword;
	}

	public String getIdCardPicId() {
		return idCardPicId;
	}

	public void setIdCardPicId(String idCardPicId) {
		this.idCardPicId = idCardPicId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
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

	public String getAccountPassword() {
		return accountPassword;
	}

	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
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
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	
	
}
