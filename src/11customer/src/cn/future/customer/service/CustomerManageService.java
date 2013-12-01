package cn.future.customer.service;

import java.text.ParseException;

import org.hibernate.exception.ConstraintViolationException;

import cn.future.common.exception.NotFindException;
import cn.future.common.exception.NotUniqueException;
import cn.future.customer.exception.IdCardException;
import cn.future.customer.exception.ModifyNotAllowedException;
import cn.future.customer.exception.PasswordUnmatchException;
import cn.future.customer.pojo.PCustomer;
import cn.future.customer.pojo.PCustomerDetailInfo;

public abstract interface CustomerManageService {

	/**
	 * 直接新增客户, 无需进行加密，所有的加密算法在，内部实现
	 * @param customer
	 * @return
	 * @throws ConstraintViolationException
	 */
	public PCustomer addCustomer(PCustomer customer) throws ConstraintViolationException;
	/**
	 * 新增客户详细信息
	 * @param customerId
	 * @param detail
	 * @return
	 */
	public PCustomerDetailInfo addCustomerDetail(String customerId,PCustomerDetailInfo detail);
	/**
	 * 通过注册基础信息，激活新增一个账户
	 * @param registerId， 预注册ID
	 * @return
	 * @throws NotFindException 
	 */
	public PCustomer addCustomerByPreRegister(String registerId) throws NotFindException;
	/**
	 * 保存
	 * @param pojo
	 * @return
	 */
	public PCustomer updateCustomer(PCustomer pojo);
	/**
	 * 更新客户的顾问
	 * @param cusId
	 * @param employId
	 * @return
	 * @throws NotFindException 
	 * @throws NotUniqueException 
	 */
	public PCustomer updatePCustomerAdviser(String cusId, String employId) throws NotFindException, NotUniqueException;
	/**
	 * 更新身份证信息
	 * @param cusId 客户号
	 * @param idcard 身份证号
	 * @param admin 如果是管理员，可以直接更新，不是管理员只能从空更新为有。
	 * @throws NotFindException 
	 * @throws ModifyNotAllowedException 
	 * @throws ParseException 
	 * @throws IdCardException 
	 */
	public PCustomer updateCustomerIdcard(String cusId, String idcard, boolean admin) throws NotFindException, ModifyNotAllowedException, ParseException, IdCardException;
	/**
	 * 修改账户密码
	 * @param id
	 * @param oldPassword
	 * @param newPassword
	 * @throws NotFindException 
	 * @throws PasswordUnmatchException 
	 */
	public void updatePassword(String id,String oldPassword,String newPassword) throws NotFindException, PasswordUnmatchException;
}
