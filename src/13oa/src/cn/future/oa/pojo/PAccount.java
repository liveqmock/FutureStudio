package cn.future.oa.pojo;

import java.util.Date;
import java.util.Set;

import cn.future.common.pojo.BasePriority;

public class PAccount extends BasePriority {
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -6771943388136120649L;
	private String account;
	private String password;
	private String employeeId; //重要的员工号
	private String email;
	private String name;
	private String pinyin;
	private String mobile;
	private PDepartment department;
	private int statusCode;
	private String statusName;
	private Date addTime;  //入职时间
	private Date leaveTime;
	//从这里开始下面的信息都是扩展出来的
	private String headerFileId;
	private String accountTypeId;
	private String accountTypeName;
	private String data1; //铭旺叫担保情况
	private String data2;//保险购买
	private String data3;//住宿情况
	private String data4;//籍贯
	private String data5;//民族
	private String data6;//学历
	private String data7;//婚否
	private String address;
	private String familyAddress;
	private String familyCall;
	private String idcard;//身份证
	private Date birthday;//出生年月日
	private Date fullDate;//转正时间
	private int yearVocation;//年假	
	
	private String content="";
	private Set<Role> roles;
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public PDepartment getDepartment() {
		return department;
	}
	public void setDepartment(PDepartment department) {
		this.department = department;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public String getAccountTypeId() {
		return accountTypeId;
	}
	public void setAccountTypeId(String accountTypeId) {
		this.accountTypeId = accountTypeId;
	}
	public String getAccountTypeName() {
		return accountTypeName;
	}
	public void setAccountTypeName(String accountTypeName) {
		this.accountTypeName = accountTypeName;
	}
	public String getData1() {
		return data1;
	}
	public void setData1(String data1) {
		this.data1 = data1;
	}
	public String getData2() {
		return data2;
	}
	public void setData2(String data2) {
		this.data2 = data2;
	}
	public String getData3() {
		return data3;
	}
	public void setData3(String data3) {
		this.data3 = data3;
	}
	public String getData4() {
		return data4;
	}
	public void setData4(String data4) {
		this.data4 = data4;
	}
	public String getData5() {
		return data5;
	}
	public void setData5(String data5) {
		this.data5 = data5;
	}
	public String getData6() {
		return data6;
	}
	public void setData6(String data6) {
		this.data6 = data6;
	}
	public String getData7() {
		return data7;
	}
	public void setData7(String data7) {
		this.data7 = data7;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFamilyAddress() {
		return familyAddress;
	}
	public void setFamilyAddress(String familyAddress) {
		this.familyAddress = familyAddress;
	}
	public String getFamilyCall() {
		return familyCall;
	}
	public void setFamilyCall(String familyCall) {
		this.familyCall = familyCall;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(java.util.Date birthday2) {
		this.birthday = birthday2;
	}
	public int getYearVocation() {
		return yearVocation;
	}
	public void setYearVocation(int yearVocation) {
		this.yearVocation = yearVocation;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getHeaderFileId() {
		return headerFileId;
	}
	public void setHeaderFileId(String headerFileId) {
		this.headerFileId = headerFileId;
	}
	public Date getFullDate() {
		return fullDate;
	}
	public void setFullDate(Date fullDate) {
		this.fullDate = fullDate;
	}
	public Date getLeaveTime() {
		return leaveTime;
	}
	public void setLeaveTime(Date leaveTime) {
		this.leaveTime = leaveTime;
	}
}
