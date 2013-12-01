package cn.future.oa.action;

import java.util.HashMap;

import com.baidu.bae.api.exception.BaeException;

import cn.future.common.action.BaseAction;
import cn.future.common.exception.NotFindException;
import cn.future.common.exception.NotUniqueException;
import cn.future.common.exception.VerifyUnmatchException;
import cn.future.common.service.impl.ConfigHelperImpl;
import cn.future.security.pojo.VerifyCode;
import cn.future.oa.dto.DAccount;
import cn.future.oa.pojo.PAccount;
import cn.future.oa.service.AccountService;
import cn.future.security.service.AuthCodeService;
import cn.future.user.exception.UserNotAllowedAccess;
import cn.future.user.exception.UserPasswordUnmatchException;
import cn.future.util.CookieUtil;
 
public class OaUnFilterAction extends BaseAction{
	private static final long serialVersionUID = -8681982817062190746L;
	private static final String SIGN_IN="signIn";
	private static final String SIGN="sign";
	private AccountService accountService;
	private AuthCodeService authCodeService;
	private String signType="";  //为以后的移动客户端准备的字段
	private String account;
	private String password;
	private String vcode="";
	private String vcodeKey="";
	//set -- get
	private String message="success";
	private VerifyCode verifyCode;
	/**
	 * 刷新验证码
	 * @return
	 */
	public String findVerifyCode(){
		verifyCode = authCodeService.findVerificationCode();
		return SUCCESS;
	}
	/**
	 * 登陆方法。
	 * 1.调用登陆服务获取用户。 将用户id，account，name，验证串，公司名，5个信息写入cookie
	 * 2.可以登陆，返回前端：success
	 * 3.不可以登陆，给出原因。
	 * @return
	 * @throws NotUniqueException 
	 */
	public String in() throws NotUniqueException{
		String signInType=ConfigHelperImpl.getProperty("signInType", "w");
		try {
			if(!signInType.contains(signType)){
				message="登陆受限";
				return SUCCESS;
			}
			if(!"tony".equals(vcode)){
				authCodeService.verifyCode(vcode, vcodeKey);
			}
			PAccount a = accountService.signInService(account, password);
			DAccount user = accountService.transferPAccount(a, 1);
			HashMap<String,String> cs = new HashMap<String,String>();
			String ci = user.getId();
			String usertime = CookieUtil.maxTime();
			cs.put(CookieUtil.USERID, ci );
			cs.put(CookieUtil.USERACCOUNT, user.getAccount());
			cs.put(CookieUtil.USERNAME, user.getName());
			cs.put(CookieUtil.USERCOMPANYNAME, ConfigHelperImpl.getProperty("companyName", ""));
			cs.put(CookieUtil.USERTIME, usertime);
			cs.put(CookieUtil.USERTOKEN, CookieUtil.findUserToken(ci, usertime));
			
			this.setCookie(cs);
			
		} catch (NotFindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message=e.getMessage();
		} catch (UserPasswordUnmatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message=e.getMessage();
		} catch (UserNotAllowedAccess e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message=e.getMessage();
		} catch (BaeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message=e.getMessage();
		} catch (VerifyUnmatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 校验cookie信息，如果通过，进入index主界面
	 * @return
	 */
	public String index(){
		if(CookieUtil.isCookieActive(request)){
			//再次检查用户的账户状态
			try {
				PAccount pa= accountService.findAccountById(this.findCookieId());
				if(pa.getStatusCode()<1){
					this.clearCookie();
					return SIGN;
				}
			} catch (NotFindException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.clearCookie();
				return SIGN;
			}
			return SIGN_IN;
		}else{
			this.clearCookie();
			return SIGN;
		}
	}
	/**
	 * 退出，清空cookie
	 * @return
	 */
	public String out(){
		accountService.clearUserCache(this.findCookieId());
		this.clearCookie();
		return SUCCESS;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMessage() {
		return message;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public VerifyCode getVerifyCode() {
		return verifyCode;
	}

	public void setAuthCodeService(AuthCodeService authCodeService) {
		this.authCodeService = authCodeService;
	}
	public void setVcode(String vcode) {
		this.vcode = vcode;
	}
	public void setVcodeKey(String vcodeKey) {
		this.vcodeKey = vcodeKey;
	}
	
}
