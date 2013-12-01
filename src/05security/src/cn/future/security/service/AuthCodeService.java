package cn.future.security.service;

import com.baidu.bae.api.exception.BaeException;

import cn.future.common.exception.VerifyUnmatchException;
import cn.future.security.pojo.VerifyCode;

public abstract interface AuthCodeService {
	/**
	 * 获取验证码
	 * @return
	 */
	public VerifyCode findVerificationCode() throws BaeException;
	/**
	 * 对验证码进行验证
	 * @param code
	 * @param secret
	 * @return
	 */
	public void verifyCode(String code, String secret) throws BaeException,VerifyUnmatchException;
}
