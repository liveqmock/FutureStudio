package cn.future.oa.action;

import cn.future.common.action.BaseAction;
import cn.future.security.pojo.VerifyCode;
import cn.future.security.service.AuthCodeService;

public class SignInAction extends BaseAction{
	private AuthCodeService authCodeService;
	//set -- get
	private static final long serialVersionUID = -2280011014402014153L;
	private VerifyCode verifyCode;
	public String execute(){
		verifyCode = authCodeService.findVerificationCode();
		return SUCCESS;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setAuthCodeService(AuthCodeService authCodeService) {
		this.authCodeService = authCodeService;
	}
	public VerifyCode getVerifyCode() {
		return verifyCode;
	}
	
}
