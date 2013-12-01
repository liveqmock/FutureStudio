package cn.future.security.action;

import cn.future.common.action.BaseAction;
import cn.future.security.pojo.VerifyCode;
import cn.future.security.service.AuthCodeService;

public class AuthCodeAction extends BaseAction {

	private static final long serialVersionUID = 393723476504130625L;
	//set
	private AuthCodeService authCodeService;
	//get
	private VerifyCode vcode;
	/**
	 * 获取一个新的Vcode
	 * @return
	 */
	public String find(){
		vcode = authCodeService.findVerificationCode();
		return SUCCESS;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setAuthCodeService(AuthCodeService authCodeService) {
		this.authCodeService = authCodeService;
	}
	public VerifyCode getVcode() {
		return vcode;
	}
	
	
}
