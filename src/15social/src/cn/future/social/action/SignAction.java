package cn.future.social.action;

import cn.future.common.action.BaseAction;
import cn.future.common.exception.NotFindException;
import cn.future.common.exception.NotUniqueException;
import cn.future.customer.pojo.PCustomer;
import cn.future.customer.service.CustomerQueryService;
import cn.future.oa.pojo.PAccount;
import cn.future.oa.service.AccountService;
import cn.future.social.pojo.PSocialBind;
import cn.future.social.service.SocialBindManageService;
import cn.future.social.service.SocialBindQueryService;
import cn.future.user.exception.UserNotAllowedAccess;
import cn.future.user.exception.UserPasswordUnmatchException;

public class SignAction extends BaseAction{
	private static final String CUSTOMER_SIGN = "customer_sign";
	private static final String CUSTOMER_SIGN_SUCCESS = "customer_sign_success";
	private static final long serialVersionUID = -4459554631286777013L;
	//set
	private CustomerQueryService customerQueryService;
	private AccountService accountService;
	private SocialBindQueryService socialBindQueryService;
	private SocialBindManageService socialBindManageService;
	private String account=null;
	private String password=null;
	//set - get
	private String openId=null;
	private String position;
	private String bindType;
	
	//set -- get
	private String message;
	
	//get
	public String in(){
		if(openId!=null){
			int k = socialBindQueryService.findOpenIdUnique(openId, bindType, position);
			if(k>0){
				message="该帐号已经绑定，请先解绑";
				return CUSTOMER_SIGN;
			}
		}
		if(account!=null && password!=null){
			if(PSocialBind.POSITION_CUSTOMER.equals(position)){
				try {
					PCustomer pc = customerQueryService.customerSignInService(account, password);
					PSocialBind pojo = new PSocialBind();//bindType, openId, pc.getId(), position
					socialBindManageService.addPSocialBind(pojo);
					message="绑定成功";
					return CUSTOMER_SIGN_SUCCESS;
				} catch (NotFindException e) {
					message="账户不存在";
					e.printStackTrace();
				} catch (UserNotAllowedAccess e) {
					message="账户不可用";
					e.printStackTrace();
				} catch (UserPasswordUnmatchException e) {
					message="密码错误";
					e.printStackTrace();
				} catch (NotUniqueException e) {
					message="系统异常请与管理员联系";
					e.printStackTrace();
				}
			}else if(PSocialBind.POSITION_USER.equals(position)){
				//暂时不用
				try {
					PAccount pc = accountService.signInService(account, password);
					PSocialBind pojo = new PSocialBind();//bindType, openId, pc.getId(), position
					socialBindManageService.addPSocialBind(pojo);
					message="绑定成功";
				} catch (NotFindException e) {
					message="账户不存在";
					e.printStackTrace();
				} catch (UserPasswordUnmatchException e) {
					message="密码错误";
					e.printStackTrace();
				} catch (UserNotAllowedAccess e) {
					message="账户不可用";
					e.printStackTrace();
				} catch (NotUniqueException e) {
					message="系统异常请与管理员联系";
					e.printStackTrace();
				}
			}
		}
		return CUSTOMER_SIGN;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public void setBindType(String bindType) {
		this.bindType = bindType;
	}

	public String getOpenId() {
		return openId;
	}

	public String getPosition() {
		return position;
	}

	public String getBindType() {
		return bindType;
	}

	public void setCustomerQueryService(CustomerQueryService customerQueryService) {
		this.customerQueryService = customerQueryService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public void setSocialBindQueryService(
			SocialBindQueryService socialBindQueryService) {
		this.socialBindQueryService = socialBindQueryService;
	}

	public String getMessage() {
		return message;
	}

	public void setSocialBindManageService(
			SocialBindManageService socialBindManageService) {
		this.socialBindManageService = socialBindManageService;
	}
	
	
}
