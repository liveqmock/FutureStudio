package cn.future.oa.dto;

import cn.future.common.pojo.BasePriority;
import cn.future.oa.pojo.PAccount;
import cn.future.oa.pojo.PDepartment;
import cn.future.util.TimeUtil;

public class DAccount extends BasePriority{

	public DAccount(){
		
	}
	/**
	 * 获取几级的部门信息
	 * @param d
	 * @param departmentFetch
	 */
	public DAccount(PAccount d, int departmentFetch){
		this(d);
		PDepartment p =d.getDepartment();
		String posi=p.getName();
		if(departmentFetch==2){
			PDepartment parent =p.getParent();
			if(parent!=null){
				posi=posi+parent.getName();
			}
		}
		this.setDepartment(posi);
	}
	public DAccount(PAccount d){
		this.account=d.getAccount();
		this.email=d.getEmail();
		this.employeeId=d.getEmployeeId();
		this.name=d.getName();
		this.pinyin=d.getPinyin();
		this.id=d.getId();
		this.priority=d.getPriority();
		this.comments=d.getComments();
		this.statusCode=d.getStatusCode();
		this.statusName=d.getStatusName();
		this.mobile=d.getMobile();
		this.addTime = TimeUtil.dateToYMDString(d.getAddTime());
		this.leavelTime=TimeUtil.dateToYMDString(d.getLeaveTime());
		this.accountTypeId=d.getAccountTypeId();
		this.accountTypeName=d.getAccountTypeName();
		this.data1=d.getData1();
		this.data2=d.getData2();
		this.data3=d.getData3();
		this.data4=d.getData4();
		this.data5=d.getData5();
		this.data6=d.getData6();
		this.data7=d.getData7();
		this.address=d.getAddress();
		this.familyAddress=d.getFamilyAddress();
		this.familyCall=d.getFamilyCall();
		this.idcard=d.getIdcard();
		this.birthday=TimeUtil.dateToYMDString(d.getBirthday());
		
		this.fullDate=TimeUtil.dateToYMDHMSString(d.getFullDate());
		this.yearVocation=d.getYearVocation();
		
		this.headerFileId=d.getHeaderFileId();
	}
	private String department;
	private String headerFileId;
	private static final long serialVersionUID = -6771943388136120649L;
	private String account;
	private String employeeId;
	private String email;
	private String name;
	private String pinyin;
	private int statusCode;
	private String statusName;
	private String mobile;
	private String addTime;
	private String leavelTime;
	
	private String accountTypeId;
	private String accountTypeName;
	private String data1; //担保情况
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
	private String birthday;//出生年月日
	private int yearVocation;//年假	
	
	private String fullDate;
	
	
	
	public String getFullDate() {
		return fullDate;
	}
	public void setFullDate(String fullDate) {
		this.fullDate = fullDate;
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

	public int getYearVocation() {
		return yearVocation;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public void setYearVocation(int yearVocation) {
		this.yearVocation = yearVocation;
	}
	
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getHeaderFileId() {
		return headerFileId;
	}
	public void setHeaderFileId(String headerFileId) {
		this.headerFileId = headerFileId;
	}
	public String getLeavelTime() {
		return leavelTime;
	}
	public void setLeavelTime(String leavelTime) {
		this.leavelTime = leavelTime;
	}

	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	
}
