package cn.future.customer.service;

import cn.future.common.exception.NotUniqueException;
import cn.future.security.pojo.PSecurityCode;
import cn.future.customer.pojo.PCustomerPreRegister;

public abstract interface CustomerPreRegisterManageService {
	/**
	 * 用户预注册，等待验证通过
	 * @param pojo 无需填写id，创建时间，安全码id
	 * @param securityCode
	 * @return
	 */
	public PCustomerPreRegister addCustomerPreRegister(PCustomerPreRegister pojo, PSecurityCode securityCode)throws NotUniqueException;
}
