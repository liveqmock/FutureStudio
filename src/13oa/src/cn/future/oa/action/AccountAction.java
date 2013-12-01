package cn.future.oa.action;

import java.util.List;

import cn.future.common.action.BaseAction;
import cn.future.common.exception.NotFindException;
import cn.future.oa.dto.DAccount;
import cn.future.oa.pojo.PAccount;
import cn.future.oa.pojo.PAccountType;
import cn.future.oa.service.AccountService;
import cn.future.util.StringUtil;
import cn.future.util.TimeUtil;

public class AccountAction  extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1137771534294209953L;
	private List<DAccount> accounts;
	private List<PAccountType> accountType;
	private String message="";
	
	//get -- set
	private String id1;
	private String id2;
	
	private String id;
	private String priority;
	private AccountService accountService;
	private String departmentId;
	private int yearVocation;
	private String password;
	private String comments;
	private String account;
	private String employeeId;
	private String email;
	private String name;
	private int statusCode=1;
	private String statusName="可用";
	private String mobile="";
	private String addTime;
	
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
	private String birthday;//出生年月日
	
	
	
	
	/**
	 * 通过职位id查找用户，直接查找id=***的用户，然后组装成Pojo对象
	 * @return
	 */
	public String findPositionAccount(){
		try {
			List<PAccount> list=accountService.findAccountByPositionId(id,false);
			accounts=accountService.transferPAccount(list, 1);
		} catch (NotFindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String switchAccountTypePriority() throws NotFindException{
		accountService.updateAccountTypePriority(id1,id2);
		message="修改成功";
		return SUCCESS;
	}
	/**
	 * 修改账户类型的信息
	 * @return
	 * @throws NotFindException 
	 */
	public String updateAccountType() throws NotFindException{
		accountService.updateAccountType(id,name,comments,priority);
		message="修改成功";
		return SUCCESS;
	}
	/**
	 * 新增账户类型
	 * @return
	 */
	public String addAccountType(){
		accountService.addAccountType(name,comments,priority);
		message="新增成功";
		return SUCCESS;
	}
	/**
	 * 查询所有的账户类型
	 * @return
	 */
	public String findAllAccountType(){
		accountType = accountService.findAllAccountType();
		return SUCCESS;
	}
	public String employeeIdCheck(){
		int i=accountService.employeeIdCount(employeeId);
		if(i<1){
			message="可用";
		}else{
			message="已经存在";
		}
		return SUCCESS;
	}
	/**
	 * 检查帐户名是否重复
	 * @return
	 */
	public String accountCheck(){
		int i=accountService.accountCount(data1);
		if(i<1){
			message="可用";
		}else{
			message="已经存在";
		}
		return SUCCESS;
	}
	public PAccount accountFactory(PAccount add){
		add.setAccount(account);
		add.setAccountTypeId(accountTypeId);
		add.setAccountTypeName(accountTypeName);
		add.setAddress(address);
		
		add.setBirthday(TimeUtil.stringToDate(birthday));
		add.setComments(comments);
		add.setYearVocation(yearVocation);
		add.setData1(data1);
		add.setData2(data2);
		add.setData3(data3);
		add.setData4(data4);
		add.setData5(data5);
		add.setData6(data6);
		add.setData7(data7);
		
		add.setEmail(email);
		add.setEmployeeId(employeeId);
		add.setFamilyAddress(familyAddress);
		add.setFamilyCall(familyCall);
		
		
		add.setIdcard(idcard);
		add.setMobile(mobile);
		add.setName(name);
		
		add.setPinyin(StringUtil.getPinyinString(name));
		
		
		add.setStatusCode(statusCode);
		add.setStatusName(statusName);
		add.setAddTime(TimeUtil.stringToDate(addTime));
		
		return add;
	}
	/**
	 * 
	 * @return
	 * @throws NotFindException 
	 */
	public String accountAdd() throws NotFindException{
		PAccount add =new PAccount();
		this.accountFactory(add);
		add.setId(StringUtil.getUuid());
		add.setPassword(StringUtil.sha512Encrypt(password));
		add.setPriority(0);
		add.setYearVocation(0);
		
		accountService.addAccount(this.findCookieId(),departmentId, add);
		return SUCCESS;
	}
	/**
	 * 更新用户信息
	 */
	public String updateAccount(){
		/**
		 * 操作用户的ID， 账户ID，名字，工号，账户，电话，邮箱，备注，账户状态代码，账户状态名称，
		 */
		PAccount add;
		try {
			add = accountService.findAccountById(id);
			accountService.updateAccount(add);		
			this.accountFactory(add);
			message="修改成功";
		} catch (NotFindException e) {
			// TODO Auto-generated catch block
			message=e.getMessage();
			response.setStatus(400);
			e.printStackTrace();
		}
		
		
		
		return SUCCESS;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<DAccount> getAccounts() {
		return accounts;
	}

	public List<PAccountType> getAccountType() {
		return accountType;
	}

	public String getMessage() {
		return message;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public void setYearVocation(int yearVocation) {
		this.yearVocation = yearVocation;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public void setAccountTypeId(String accountTypeId) {
		this.accountTypeId = accountTypeId;
	}

	public void setAccountTypeName(String accountTypeName) {
		this.accountTypeName = accountTypeName;
	}

	public void setData1(String data1) {
		this.data1 = data1;
	}

	public void setData2(String data2) {
		this.data2 = data2;
	}

	public void setData3(String data3) {
		this.data3 = data3;
	}

	public void setData4(String data4) {
		this.data4 = data4;
	}

	public void setData5(String data5) {
		this.data5 = data5;
	}

	public void setData6(String data6) {
		this.data6 = data6;
	}

	public void setData7(String data7) {
		this.data7 = data7;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setFamilyAddress(String familyAddress) {
		this.familyAddress = familyAddress;
	}

	public void setFamilyCall(String familyCall) {
		this.familyCall = familyCall;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}
	public void setId1(String id1) {
		this.id1 = id1;
	}
	public void setId2(String id2) {
		this.id2 = id2;
	}
	
	
}
