package cn.future.customer.service;

import java.util.List;

import cn.future.common.exception.NotFindException;
import cn.future.common.exception.VerifyUnmatchException;
import cn.future.customer.dto.DCustomerPreRegister;
import cn.future.customer.pojo.PCustomerPreRegister;

public abstract interface CustomerPreRegisterQueryService {
	/**
	 * 
	 * @param pojos
	 * @return
	 */
	public List<DCustomerPreRegister> transfer(List<PCustomerPreRegister> pojos);
	/**
	 * 
	 * @param pojo
	 * @return
	 */
	public DCustomerPreRegister transfer(PCustomerPreRegister pojo);
	/**
	 * 验证用户注册通过
	 * @param id
	 * @param vcodeKey
	 * @param vcode
	 * @throws NotFindException 
	 * @throws VerifyUnmatchException 
	 */
	public void validatePreRegisterKey(String id, String vcodeKey) throws NotFindException, VerifyUnmatchException;
}
