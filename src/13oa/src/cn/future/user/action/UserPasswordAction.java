package cn.future.user.action;

import cn.future.common.action.BaseAction;
import cn.future.common.exception.NotFindException;
import cn.future.oa.service.AccountService;
import cn.future.user.exception.UserPasswordUnmatchException;

public class UserPasswordAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8356518318835882540L;
	private String password;
	private String newPassword;
	private AccountService accountService;
	//set --  get
	
	private String message;
	public String changePassword() throws NotFindException{
		try {
			accountService.updatePassword(password, newPassword, this.findCookieId());
			message="修改成功";
		} catch (UserPasswordUnmatchException e) {
			// TODO Auto-generated catch block
			this.response.setStatus(400);
			this.message=e.getMessage();
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getMessage() {
		return message;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	
	
}
