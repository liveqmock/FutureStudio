package cn.future.customer.service;

import cn.future.common.exception.NotFindException;
import cn.future.common.exception.NotUniqueException;
import cn.future.customer.dto.DCustomer;
import cn.future.customer.exception.DCustomerFactoryNotFindDtoException;
import cn.future.customer.pojo.PCustomer;

public abstract interface DCustomerFactory {
	/**
	 * 通过ID初始化
	 * @param id
	 * @return
	 * @throws NotFindException 
	 */
	public DCustomerFactory initById(String id) throws NotFindException;
	/**
	 * 通过持久对象初始化
	 * @param pojo
	 * @return
	 */
	public DCustomerFactory initByPojo(PCustomer pojo);
	/**
	 * 获取dto对象
	 * @return
	 * @throws DCustomerFactoryNotFindDtoException 
	 */
	public DCustomer getDCustomer() throws DCustomerFactoryNotFindDtoException;
	/**
	 * 初始化信用信息
	 * @return
	 * @throws DCustomerFactoryNotFindDtoException 
	 */
	public DCustomerFactory initCredit() throws DCustomerFactoryNotFindDtoException;
	/**
	 * 初始化详细信息
	 * @return
	 * @throws DCustomerFactoryNotFindDtoException 
	 * @throws NotFindException 
	 */
	public DCustomerFactory initDetailInfo() throws DCustomerFactoryNotFindDtoException, NotFindException;
	/**
	 * 初始化客户负责人打信息
	 * @return
	 * @throws DCustomerFactoryNotFindDtoException 
	 * @throws NotUniqueException 
	 * @throws NotFindException 
	 */
	public DCustomerFactory initAdviser() throws DCustomerFactoryNotFindDtoException, NotFindException, NotUniqueException;
}
